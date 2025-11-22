package com.shubham.todoapp.Service;

import com.shubham.todoapp.Dto.TaskResponse;
import com.shubham.todoapp.Entity.DailyTask;
import com.shubham.todoapp.Entity.User;
import com.shubham.todoapp.Repository.TaskRepo;
import com.shubham.todoapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    UserService userService;


    public List<DailyTask> getAll(){
        return taskRepo.findAll();
    }


    public  void  saveTask(DailyTask dailyTask){
         taskRepo.save(dailyTask);
    }
//public  void  saveTask(DailyTask dailyTask,String userName){
//        try {
//            User user  = userService.findByUserName(userName);
//
//            DailyTask task = taskRepo.save(dailyTask);
//
//            user.getTasks().add(task);
//            userService.saveUser(user);
//
//
//        }
//        catch (Exception e){
//            System.out.println("Exception"+e);
//            throw  new RuntimeException("An error occured while saving the task"+e);
//        }
//
//
//    }


    public Optional<DailyTask> getById(Long id){
        return taskRepo.findById(id);
    }


    public void deleteTask(Long id){
    taskRepo.deleteById(id);
    }
}
