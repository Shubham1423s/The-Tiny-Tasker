package com.shubham.todoapp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Indexed;

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

    @NotBlank(message = "Task cannot be blank")
    // this will throw an exception and we have to handle the exception using exception handling
   @Column(unique = true,nullable = false)
    // for this to execute we have to explicitly tell database to store only unique  values
    // with telling this will not work

    private   String firstName;

  private   String lastName;
    @Email(message = "must be a valid email")
    @Column(unique = true,nullable = false)

   private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DailyTask> tasks;

}
