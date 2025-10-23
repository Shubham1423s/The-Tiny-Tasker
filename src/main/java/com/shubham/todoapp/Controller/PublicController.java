package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Dto.UserResponse;
import com.shubham.todoapp.Entity.DailyTask;
import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Service.TaskService;
import com.shubham.todoapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yes")

public class PublicController {

    @Autowired
    public TaskService taskService;

    @Autowired
    UserService userService;

    // in this we gonna combine both the things task and user
    @GetMapping("/SaveTaskByUser")
    public ResponseEntity<UserResponse<User>> createTaskByUser(@RequestBody User user, @RequestBody DailyTask task){

        userService.saveUser(user);
        taskService.saveTask(task);



        return ResponseEntity.status(HttpStatus.CREATED).body( new UserResponse<>(user,"Task Created Successfully"));


    }

}
