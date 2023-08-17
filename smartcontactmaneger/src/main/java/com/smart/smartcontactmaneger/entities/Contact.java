package com.smart.smartcontactmaneger.entities;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact {
    // @Override
    // public String toString() {
    //     return "Contact [cid=" + cid + ", name=" + name + ", secondname=" + secondname + ", email=" + email + ", phone="
    //             + phone + ", image=" + image + ", work=" + work + ", description=" + description + ", user=" + user
    //             + "]";
    // }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;
    private String name;
    private String secondname;
    private String email;
    private String phone;
    private String image;
    public String getWork() {
        return work;
    }
    public void setWork(String work) {
        this.work = work;
    }

    private String work;
    @Column(length=1000)
    
    private String description;

    @ManyToOne
    @JsonIgnore
    private User user;
    public User getUser() {
    return user;
}
public void setUser(User user) {
    this.user = user;
}
    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSecondname() {
        return secondname;
    }
    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Contact(){

        super();
    }


    

@Override
public boolean equals(Object obj){


    return this.cid==((Contact)obj).getCid();
}}