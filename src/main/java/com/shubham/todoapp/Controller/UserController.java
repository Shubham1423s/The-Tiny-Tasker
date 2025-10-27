package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Dto.UserResponse;
import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.UserRepo;
import com.shubham.todoapp.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/User")// so it can handle http requests
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/Save")
    public ResponseEntity<UserResponse<User>> saveUser( @Valid @RequestBody User user){

        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse<>(user,"User Created Successfully"));
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<UserResponse<User>>deleteUserById(@PathVariable("id") Long id){

        Optional<User> user = userService.getUser(id);
        if(user.isPresent()){
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponse<>(user.get(),"User Deleted Successfully"));
        }
        else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>(null,"User Not Found"));

        }
    }

    @PutMapping("/UpdateUser/{id}")
    public ResponseEntity<UserResponse<User>> updateUserById(@RequestBody User user, @PathVariable("id") Long id ){

      Optional<User> user1 = userService.getUser(id);

      if (user1.isPresent()) {

          User oldUser = user1.get();
          oldUser.setFirstName(user.getFirstName());
          oldUser.setEmail(user.getEmail());
          oldUser.setLastName(user.getLastName());

          userService.saveUser(oldUser);

          return ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(user,"User Updated Successfully"));
      }
      else{
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>(null,"User Not Found"));

      }

    }

    @GetMapping("/AllUser")
    public ResponseEntity<UserResponse<List<User>>> AllUser(){


        List<User> allUser = userService.getAllUser();
        if(allUser != null && !allUser.isEmpty()){
            return  ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(allUser,"Fetch All User"));

        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponse<>(null,"No User Found"));



    }

    @GetMapping("/fetchUser/{id}")
    public ResponseEntity<UserResponse<User>> getUserById(@PathVariable("id") Long id){

        Optional<User> user = userService.getUser(id);

        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(user.get(),"User Found "));

        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>(null,"User Not Found"));
        }



    }

}
