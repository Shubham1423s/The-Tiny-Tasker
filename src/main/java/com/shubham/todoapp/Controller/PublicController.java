package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Dto.UserResponse;
import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    //here gonna put the public methods
   @PostMapping("/login")
    public ResponseEntity<UserResponse<User>> login (@RequestBody User user ){
      try {
          userService.saveNewUser(user);
          return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse<>(user,"logged in Successfully"));


      }
      catch (Exception e){
          e.printStackTrace(); // TEMP: Print real cause
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body(new UserResponse<>(null, "Failed to create user"));
      }


    }

}
