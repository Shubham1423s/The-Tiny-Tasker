package com.shubham.todoapp.Service;

import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

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


}
