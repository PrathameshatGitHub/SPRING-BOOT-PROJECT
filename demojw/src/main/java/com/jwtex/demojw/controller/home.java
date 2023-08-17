package com.jwtex.demojw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class home {
  
    
    @RequestMapping("/welcome")
public String welcome()
{

    return"this controller has not been seen by the auntherized user";
}
}
