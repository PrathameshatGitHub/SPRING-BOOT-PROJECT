package com.smart.smartcontactmaneger.dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.smartcontactmaneger.entities.Contact;
import com.smart.smartcontactmaneger.entities.User;

public interface ContactRepository extends JpaRepository<Contact,Integer> {


    // contacts repository implemted for paggination implementaion

@Query("from Contact as c where c.user.id=:userId")

// currentpage-page
// contacts per page=5
public Page<Contact>FindContactByUser( @Param("userId")int userId,Pageable pageable);
   

public List<Contact>findByNameContainingAndUser(String Name,User user);
}
