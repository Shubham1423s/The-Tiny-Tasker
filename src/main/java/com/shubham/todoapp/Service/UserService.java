package com.shubham.todoapp.Service;

import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<User> getUser(Long id){
       return userRepo.findById(id);
    }

    public List<User> getAllUser(){

      return   userRepo.findAll();
    }
    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    public void saveUser(User user){


        userRepo.save(user);
    }
    public void saveNewUser(User user){

//        if(userRepo.existsByFirstName(user.getFirstName())){
//
//            throw new RuntimeException("Username already exists");
//        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);


    }
    public void saveNewAdmin(User user){

        if(userRepo.existsByFirstName(user.getFirstName())){

            throw new RuntimeException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("Admin"));
        userRepo.save(user);


    }

    public User findByUserName(String firstName){
        return  userRepo.findByFirstName(firstName);
    }



}
