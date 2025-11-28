package com.shubham.todoapp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shubham.todoapp.Enums.Priority;
import com.shubham.todoapp.Enums.TaskStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;
import java.time.LocalDate;

@Entity
@Table(name = "dailyTasks")
@Getter
@Setter
public class DailyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Task can not be blank")
    @Column(nullable = false)
    private  String title;

    private  String description;

    @Column(nullable = false)
    private Priority priority = Priority.LOW;

    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;

    @Column(nullable = false)
    private LocalDate taskDate;

    // i have to add one more thing is to calculate the percentage of each task

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


}
