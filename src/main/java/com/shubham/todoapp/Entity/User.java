package com.shubham.todoapp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Indexed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name  cannot be blank")
    @Column(unique = true, nullable = false)
    private   String firstName;

    private   String lastName;
    @NotBlank(message = "Password can not be blank ")
    @Column(nullable = false)
    private  String password;

    @Email(message = "must be a valid email")
    @Column(unique = true,nullable = false)
     private String email;

    @Column(nullable = false)

    private List<String> roles;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailyTask> tasks = new ArrayList<>();

}
