package com.smart.smartcontactmaneger.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.smart.smartcontactmaneger.dao.UserRepository;
import com.smart.smartcontactmaneger.entities.User;
import com.smart.smartcontactmaneger.helper.message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class Homecontroller {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
@Autowired
    private UserRepository userRepository;

 @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title", "this is the home ");

        return"home";
    }
  

    @RequestMapping("/about")
    public String base(Model m){
        m.addAttribute("title","this is the base title");

        return"about";
    }
    
    @RequestMapping("/signup")
    public String signup(Model mo){
        mo.addAttribute("title","this is the signup title");
        mo.addAttribute("user", new User());

        return"signup";
    }

    @PostMapping("/do_register")
    public String register( @Valid @ModelAttribute("user")User user ,BindingResult result1 ,@RequestParam (value="agreement",defaultValue = "false")boolean agreement, Model model, HttpSession session){
     
     try {
      
      if(!agreement){
        
        System.out.println("u have not checked the agreement");
      throw new Exception("you have to agree terms and conditions");
      }
      if(result1.hasErrors()){
        System.out.println("ERROR"+result1.toString());
        model.addAttribute("user", user);
        return"signup";
      }
      user.setRole("ROLE_USER");
      user.setEnabled(true);
      user.setImageurl("default.png");
      user.setPassword(passwordEncoder.encode(user.getPassword()));

      
        System.out.println("Agreement"+agreement);
      System.out.println("USER"+user);
      

       User result=this.userRepository .save(user);
      
       
      model.addAttribute("user", new User());
      session.setAttribute("message", new message("successfully registered!!!","alert-success"));
       return"signup";
       
     } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("user", user);
      session.setAttribute("message", new message("something went wrong!!!"+e.getMessage(),"alert-danger"));
      return"signup";
    }
     



    }


    // handler for custom login page
   
   
    @RequestMapping("/signin")
    public String Login(Model model){
        model.addAttribute("title","this is the login page");

        return"login";
    }
}
