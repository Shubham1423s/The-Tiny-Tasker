package com.shubham.todoapp.Service;

import com.shubham.todoapp.Dto.TaskResponse;
import com.shubham.todoapp.Entity.DailyTask;
import com.shubham.todoapp.Repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;


    public List<DailyTask> getAll(){
        return taskRepo.findAll();
    }

    public  void  saveTask(DailyTask dailyTask){
         taskRepo.save(dailyTask);
    }

    public Optional<DailyTask> getById(Long id){
        return taskRepo.findById(id);
    }

    public void deleteTask(Long id){
    taskRepo.deleteById(id);
    }
}
