package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Dto.UserResponse;
import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.UserRepo;
import com.shubham.todoapp.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {



    @Autowired
    private UserRepo userRepo;

    @Autowired
    private   UserService userService;





    @PostMapping("/Save")
    public ResponseEntity<UserResponse<User>> saveUser( @Valid @RequestBody User user){

        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse<>(user,"User Created Successfully"));
    }



    @PutMapping("/UpdateUser")
    public ResponseEntity<UserResponse<User>> updateUserById(@RequestBody User newUser ){

        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUserName(userName);

        if(userInDb != null){
            userInDb.setFirstName(newUser.getFirstName());
            userInDb.setPassword(newUser.getPassword());

            userService.saveNewUser(userInDb);
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(newUser,"User Updated Successfully"));

        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>(null,"user Not found"));

        }

    }

    @GetMapping("/find")
    public ResponseEntity<UserResponse<User>> getByUserName(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUserName(name);

        if(user != null ){
            return  ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(user,"user Found"));

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>(null,"User Not Found"));

        }

    }

    @DeleteMapping("/Delete")
    public ResponseEntity<UserResponse<User>>deleteUserByUserName(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepo.deleteByFirstName(userName);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse<>(null,"user Deleted Successful"));
    }

}
