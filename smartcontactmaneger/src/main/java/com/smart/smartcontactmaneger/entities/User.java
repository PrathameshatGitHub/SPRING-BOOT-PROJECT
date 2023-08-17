package com.smart.smartcontactmaneger.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
 @NotBlank(message = "Name Should not be blank")
 @Size(min=3,max=20, message="min 2 nd max 20 charactor required")
    private String name;
    @NotBlank(message = "Password Should not be blank")
    private String password;

    @Column(unique = true)
     
    @NotBlank(message = "please enter email first !!")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" ,message="Invalid Email")
    private String email;

    private String imageurl;

    @Column(length = 500)
    @NotBlank(message = "tell something about yourself")
    private String about;

    private boolean enabled;

    private String role;

   @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();

    public int getId() {
        return id;
    }


    public List<Contact> getContacts() {
        return contacts;
    }


    public void setContacts(List<Contact> contacts) {
        contacts = contacts;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getImageurl() {
        return imageurl;
    }


    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    public String getAbout() {
        return about;
    }


    public void setAbout(String about) {
        this.about = about;
    }


    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    
    

    public User(){
        super();
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", imageurl="
                + imageurl + ", about=" + about + ", enabled=" + enabled + ", role=" + role + ", contacts=" + contacts
                + "]";
    }
    
}
