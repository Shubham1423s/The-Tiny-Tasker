package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Dto.TaskResponse;
import com.shubham.todoapp.Dto.UserResponse;
import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.UserRepo;
import com.shubham.todoapp.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
   private UserService userService;

    @Autowired
    private UserRepo userRepo;


      @PostMapping("/create-admin")
      public ResponseEntity<UserResponse<User>> newAdmin(@RequestBody  User user){

            userService.saveNewAdmin(user);

         return  ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse<>(user,"Admin created"));

      }

    @GetMapping("/All-User")

    public ResponseEntity<UserResponse<List<User>>> alluser(){


            List<User> users = userService.getAllUser();
            if(users != null && !users.isEmpty()){
                return  ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(users,"All users"));

        }

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponse<>(null,"no User Found"));

    }

}
