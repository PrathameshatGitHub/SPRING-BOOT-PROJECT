package com.smart.smartcontactmaneger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.smartcontactmaneger.dao.UserRepository;
import com.smart.smartcontactmaneger.entities.User;

public class UserDetailServiceimpl implements UserDetailsService{

@Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    fetching user form the database
User user = userRepository.getUserByUserName(username);


if(user==null){

    throw new UsernameNotFoundException("could not found user");
}
CustomUserDetails customUserDetails=new CustomUserDetails(user);
   return customUserDetails;
}
    
}
