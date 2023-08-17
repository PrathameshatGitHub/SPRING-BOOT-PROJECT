package com.smart.smartcontactmaneger.Controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

  

@Controller
public class emailcontroller {
    

    @GetMapping("/forgot")
    public String emailcontro(){

        return "forgot_email";
    }


     @PostMapping("/send-otp")
    public String sendpot(@RequestParam("email") String email){

        System.out.println("EMail"+email);


        // genertaing otp of 4 digit
        Random random=new Random(1000);

        int otp=random.nextInt(9999);
        System.out.println(otp+"opt");

// code for sendiing oopt to mail


        return "varify_otp";
    }
}
