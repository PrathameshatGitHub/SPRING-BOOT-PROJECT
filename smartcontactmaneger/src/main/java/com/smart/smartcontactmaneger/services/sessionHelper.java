package com.smart.smartcontactmaneger.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import jakarta.servlet.http.HttpSession;

@Component
public class sessionHelper {

    public void removeMessageFromSession(){

        try{
System.out.println("removing messge from session ");
HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
session.removeAttribute("message");

        }
        catch (Exception e) {
    e.printStackTrace();
        }
    }
    
}
