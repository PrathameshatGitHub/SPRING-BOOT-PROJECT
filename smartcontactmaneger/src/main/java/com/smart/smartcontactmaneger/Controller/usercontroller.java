package com.smart.smartcontactmaneger.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.smartcontactmaneger.dao.ContactRepository;

import com.smart.smartcontactmaneger.dao.UserRepository;
import com.smart.smartcontactmaneger.entities.User;
import com.smart.smartcontactmaneger.helper.message;
import com.smart.smartcontactmaneger.entities.Contact;

import jakarta.persistence.criteria.Path;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;

@Controller
@RequestMapping("/user")


public class usercontroller {
    @Autowired
    private UserRepository userRepository;
  @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
private ContactRepository contactRepository;
   

public void addcommondata(Model m,Principal principal){

        String username=principal.getName();
      User user= userRepository.getUserByUserName(username);
      System.out.println(user);
      m.addAttribute("user", user);


    }
    

// home dashboard
    @RequestMapping("/index")
    public String dashboard(Model model,Principal principal){
       model.addAttribute("title","thi");


        String username=principal.getName();
      User user= userRepository.getUserByUserName(username);
      System.out.println(user);
      model.addAttribute("user", user);


        return"normal/user_dashboard";
    }

    // open add handler
    @GetMapping("/add-contact")
    public  String addcontact(Model model,Principal principal){

        String username=principal.getName();
      User user= userRepository.getUserByUserName(username);
      System.out.println(user);
      model.addAttribute("user", user);
      model.addAttribute("title", "add");
      

      model.addAttribute("contact", new Contact()  );
     

     return "normal/add-contact";
    }

    // adding the contact in daTABSE
    /**
     *
     */
    @PostMapping("/process-contact")
    public String processaddcontact(@ModelAttribute Contact contact,@RequestParam("profileImage") MultipartFile file, Principal principal,HttpSession session){

    try{  
  String name=principal.getName();
  User user =this.userRepository.getUserByUserName(name);

// process of uploading the file


if (file.isEmpty()){

  System.out.println("this file is empty");
contact.setImage("thiscon.png");
}
else{
  contact.setImage(file.getOriginalFilename());

  
  File savefile=new ClassPathResource("static/image").getFile();
  java.nio.file.Path path=Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
 Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
System.out.println("image uploaded");

}

// if(8>4){
//   throw new Exception();
// }

  contact.setUser(user);
  user.getContacts().add(contact);
  this.userRepository.save(user);

      System.out.println("data +"+contact);
      System.out.println("added to databse");
    

      // message sucess

session.setAttribute("message",new message("YOUR CONTACT IS ADDED!!ADD NEW ONE", "success"));
    
    }

      catch( Exception e){
      
       e.printStackTrace();

// error message

       session.setAttribute("message",new message("SOMETHING WENT WRONG!!TRY AGAIN", "danger"));
      }
      return"normal/add-contact";
      
    }



    // show contact handler
    // per page=5
    // and current page=0[page]
@GetMapping("/show-contacts/{page}")
    public String showcontact(@PathVariable("page")Integer page, Model m,Principal principal){
      // sending contact list
      m.addAttribute("title", "show user contacts");

      String username=principal.getName();
      User user=this.userRepository.getUserByUserName(username);

Pageable pageable=PageRequest.of(page,5);

Page<Contact>contacts=this.contactRepository.FindContactByUser(user.getId(),pageable);
m.addAttribute("contacts", contacts);
m.addAttribute("currentpage", page);
m.addAttribute("totalpages", contacts.getTotalPages());

      return "normal/show-contacts";
    }


    // showing details of perticular contact

    @RequestMapping("/{cid}/contact/")
    public String contactdetail(@PathVariable("cid") Integer cid,Model model,Principal principal){

   Optional<Contact>cOptional =this.contactRepository.findById(cid);
Contact contact=cOptional.get();


//
String username=principal.getName();
User user=this.userRepository.getUserByUserName(username);

if (user.getId()==contact.getUser().getId()) {
  model.addAttribute("contact", contact);
  model.addAttribute("title",contact.getName());
}


      System.out.println("CID"+cid);
      return"normal/contact-detail";
    }


    // delete contact handler

    @GetMapping("/delete/{cid}")
    public String deletehabdler(@PathVariable ("cid") Integer cid, Model model,Principal principal,HttpSession session){

      System.out.println(cid+"cid");
      Optional <Contact> cOptional=  this.contactRepository.findById(cid);
      Contact contact=cOptional.get();


      // deleting old photo from databse


User user=this.userRepository.getUserByUserName(principal.getName());
user.getContacts().remove(contact);
this.userRepository.save(user);

       String username=principal.getName();
User user1=this.userRepository.getUserByUserName(username);
      
      if (user1.getId()==contact.getUser().getId()) {

        contact.setUser(null);

  String image= contact.getImage();

System.out.println(image);

  this.contactRepository.delete(contact);
      }
  session.setAttribute("message",new message("YOUR CONTACT IS DELETED SUCCESFULLY", "success"));

      return"redirect:/user/show-contacts/0";
    }


    // open upadate form handler

    @PostMapping("update-contact/{cid}")
    public String updatehandler( @PathVariable("cid") Integer cid,Model model){
model.addAttribute("title ", "update-form");
      Contact contact=this.contactRepository.findById(cid).get();
model.addAttribute("contact", contact);
return "normal/update-form";
    }


// update contact handler

@PostMapping("/process-update")
public String updatehandlerr(@ModelAttribute Contact contact,@RequestParam("profileImage")MultipartFile file,Model model ,HttpSession session,Principal principal){

  try {


   Contact oldcontactdetail=this.contactRepository.findById(contact.getCid()).get();
   
   
   if(!file.isEmpty()){

      // file work
      // rewrite
      // delete old photo
      File deletefile=new ClassPathResource("static/image").getFile();
      File file1=new File(deletefile,oldcontactdetail.getImage());
      file1.delete();
      // upudate new photo
File savefile=new ClassPathResource("static/image").getFile();
  java.nio.file.Path path=Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
 Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

 contact.setImage(file.getOriginalFilename());


    }
    else{

      contact.setImage(oldcontactdetail.getImage());
    }


User user=this.userRepository.getUserByUserName(principal.getName());
   contact.setUser(user);

this.contactRepository.save(contact);
session.setAttribute("message",new message("YOUR CONTACT IS UPDATED SUCCESFULLY", "success"));

  } catch (Exception e) {
    // TODO: handle exception
  }

  
  
  System.out.println("Contact Name"+contact.getName());
System.out.println("Contact ID"+contact.getCid());
return "redirect:/user/" +contact.getCid()+"/contact/";
}



//  your profile handler


@GetMapping("/profile")
public String profile(Model model,Principal principal)
{String username=principal.getName();
      User user= userRepository.getUserByUserName(username);
      System.out.println(user);
       model.addAttribute("user", user);
  model.addAttribute("title", "profile");

return "normal/profile";
  }

// open setting handler


@GetMapping("/setting")
public String opensetting(){

  return("normal/setting");
}


// change pass handler?


@PostMapping("/change-password")
public String changepasss(@RequestParam("oldPassword")String oldPassword, @RequestParam("newPassword")String newPassword, String string,Principal principal,HttpSession session){

  System.out.println("oldpassword"+oldPassword);
  System.out.println("newpassword"+newPassword);

String username=  principal.getName();
User currentuser=this.userRepository.getUserByUserName(username);
  
System.out.println(currentuser.getName());
System.out.println(currentuser.getPassword());


if(this.bCryptPasswordEncoder.matches(newPassword, currentuser.getPassword()))
  {

    currentuser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
  this.userRepository.save(currentuser);
  session.setAttribute("message",new message("YOUR  PASSWORD CHANGED SUCCESFULLY", " success"));
  }
   
  else{
    
    session.setAttribute("message",new message("PLEASE ENTER CORRECT OLD PASSWORD", "danger"));
  
return"redirect:/user/setting"  ;
  }
  return "redirect:/user/index";
}

}