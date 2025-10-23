package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {


   @GetMapping("/HealthCheck")
   public String healthCheck(){

       return "Everything is fine";

   }
}
