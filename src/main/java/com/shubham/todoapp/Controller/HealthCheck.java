package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {



   @GetMapping("/HealthCheck")
   public String healthCheck(){


       return "Everything is fine";

   }

   @GetMapping("/loggedIn")
    public ResponseEntity<String> check(){


       if(SecurityContextHolder.getContext().getAuthentication().getName() != null){
           return ResponseEntity.ok(" Current user -  "+ SecurityContextHolder.getContext().getAuthentication().getName());
       }
       return ResponseEntity.ok("1-1");
   }



}
