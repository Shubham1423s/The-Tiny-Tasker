package com.shubham.todoapp.Service;

import com.shubham.todoapp.Dto.TaskResponse;
import com.shubham.todoapp.Entity.DailyTask;
import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.TaskRepo;
import com.shubham.todoapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

@Service
public class TaskService {

    @Autowired
   private TaskRepo taskRepo;

    @Autowired
   private UserService userService;

    @Autowired
    private UserRepo userRepo;




    public List<DailyTask> getAll(){

        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userRepo.findByFirstName(userName);

        List<DailyTask> allTask = taskRepo.findByUser(user);

        return allTask;

    }


    public  DailyTask  saveTask(DailyTask dailyTask){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userRepo.findByFirstName(userName);
        dailyTask.setUser(user);
        return taskRepo.save(dailyTask);
    }



    public Optional<DailyTask> getById(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return taskRepo.findById(id);
    }


    public void deleteTask(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    taskRepo.deleteById(id);
    }

}
