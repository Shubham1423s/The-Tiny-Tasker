package com.shubham.todoapp.Service;

import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user  = userRepo.findByFirstName(username);


        if(user != null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getFirstName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();

        }
        throw  new UsernameNotFoundException("user not found " + username);

    }
}
