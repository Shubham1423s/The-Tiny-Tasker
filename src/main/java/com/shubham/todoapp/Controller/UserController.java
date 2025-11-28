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
    @GetMapping("/find")
    public ResponseEntity<UserResponse<User>> getByUserNamee(){

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
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<UserResponse<User>>deleteUserById(@PathVariable long id){
        Optional<User> user  = userService.getUser(id);

        if(!user.isPresent() ){
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponse<>(user.get(),"User deleted Successfully"));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>(null,"User Not Found"));


    }

}
