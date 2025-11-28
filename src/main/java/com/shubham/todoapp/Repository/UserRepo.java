package com.shubham.todoapp.Repository;

import com.shubham.todoapp.Entity.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User   findByFirstName(String firstName);
    User   deleteByFirstName(String firstName);
    boolean existsByFirstName(String userName);
    // later we will fix for email also and that time i will do it also with workbench



}
