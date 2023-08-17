package com.email.emailapi.service;

import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;



@Service
public class emailservice {
    
    /**
     * @param message
     * @param subject
     * @param to
     * @return
     */
    public boolean sendEmail(String message,String subject,String to){
    
    
		boolean f=false;
		
		String from = "tatya3162@gmail.com";
		
//		sendEmail(message,subject,to,from);
		
	

	//this is responsible to send the message with attachment
	/**
	 * @param message
	 * @param subject
	 * @param to
	 * @param from
	 */
	

		//Variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("tatya3162@gmail.com", "gbmusshgfgxvuhel");
			}
			
			
			
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
		m.setText("blabla");
		Transport.send(m);
		
		
 f=true;
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	
		System.out.println("Sent success...................");
		
        return f;
		
    }}

	//this is responsible to send email..	
	
