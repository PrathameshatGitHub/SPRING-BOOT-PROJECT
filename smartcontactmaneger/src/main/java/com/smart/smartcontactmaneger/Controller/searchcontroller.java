package com.smart.smartcontactmaneger.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smart.smartcontactmaneger.dao.ContactRepository;
import com.smart.smartcontactmaneger.dao.UserRepository;
import com.smart.smartcontactmaneger.entities.Contact;
import com.smart.smartcontactmaneger.entities.User;

@RestController
public class searchcontroller {
  
    @Autowired
private UserRepository userRepository;

@Autowired
private ContactRepository contactRepository;
 

    // sreachhandle

    @GetMapping("/search/{query}")
    public ResponseEntity<?>search(@PathVariable("query") String query,Principal principal){
System.out.println(query);

User user=this.userRepository.getUserByUserName(principal.getName());
 List<Contact>contacts=this.contactRepository.findByNameContainingAndUser(query,user );
return ResponseEntity.ok(contacts);
    }
}
