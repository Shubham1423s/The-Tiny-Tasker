package com.shubham.todoapp.Service;

import com.shubham.todoapp.Repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import  static org.mockito.Mockito.*;

@SpringBootTest
public class UserDetailsServiceImplTests {

    @Autowired
    private  UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;


    @Test
    void loadUserByUsernameTest(){
        when(userRepo.findByFirstName(ArgumentMatchers.anyString())).thenReturn((com.shubham.todoapp.Entity.User) User.builder().username("ram").password("xyz").roles(String.valueOf(new ArrayList<>())).build());
       UserDetails user =  userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);

    }
}
