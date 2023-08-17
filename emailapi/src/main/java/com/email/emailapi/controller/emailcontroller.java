package com.email.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.emailapi.model.EmailRequest;
import com.email.emailapi.service.emailservice;

@RestController
public class emailcontroller {
    

    @Autowired
private emailservice em;

    @GetMapping("/welcome")
    public String welcome(){

        return "this is the welcome to the email api";

    }

    // api to send email

    @PostMapping("/sendemail")
public ResponseEntity<?> sendemail(@RequestBody EmailRequest emailRequest){

    //this.em.sendEmail(null, null, null)
    System.out.println(emailRequest);
     boolean result= this.em.sendEmail(emailRequest.getMessage(), emailRequest.getSubject(),emailRequest.getTo());

     if(result){
            return ResponseEntity.ok("done");

     }
     else{
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("emial is not sent");

     }

    }

}
