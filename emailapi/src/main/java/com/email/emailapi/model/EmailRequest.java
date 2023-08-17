package com.email.emailapi.model;

import org.springframework.http.converter.xml.SourceHttpMessageConverter;

public class EmailRequest {
    

private String subject;
private String message;
private String to;
public EmailRequest(String to, String subject, String message) {
    this.to = to;
    this.subject = subject;
    this.message = message;
}
@Override
public String toString() {
    return "EmailRequest [subject=" + subject + ", message=" + message + ", to=" + to + "]";
}
public String getTo() {
    return to;
}
public void setTo(String to) {
    this.to = to;
}
public String getSubject() {
    return subject;
}
public void setSubject(String subject) {
    this.subject = subject;
}
public String getMessage() {
    return message;
}
public void setMessage(String message) {
    this.message = message;


}
}