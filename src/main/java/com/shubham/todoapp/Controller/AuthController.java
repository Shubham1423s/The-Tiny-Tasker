package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Configuration.JwtUtil;
import com.shubham.todoapp.Dto.AuthRequest;
import com.shubham.todoapp.Dto.AuthResponse;
import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> singup(@RequestBody User user){
        if(userRepo.findByFirstName(user.getFirstName())!= null){
            return   ResponseEntity.badRequest().body("user Already Exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("User"));
        userRepo.save(user);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

        String token = jwtUtil.generateToken(request.getUserName());
        return ResponseEntity.ok(new AuthResponse(token));
    }




}
