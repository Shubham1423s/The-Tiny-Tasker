package com.shubham.todoapp.Controller;

import com.shubham.todoapp.Dto.TaskResponse;
import com.shubham.todoapp.Entity.DailyTask;
import com.shubham.todoapp.Repository.TaskRepo;
import com.shubham.todoapp.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/Tasks")
public class TaskController {



    @Autowired
    TaskService taskService;

    @GetMapping("/fetchTask/{id}")
    public ResponseEntity<TaskResponse<DailyTask>> getTaksByID(@PathVariable("id")  Long id){

       Optional<DailyTask> task  = taskService.getById(id);

                       if(task.isPresent()){
                           return  ResponseEntity.status(HttpStatus.OK).body(new TaskResponse<>(task.get(),"Task  Found"));

        }
           else{
               return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TaskResponse<>(null,"Task Not Found"));
                       }

    }
    @GetMapping("/allTask")
    public ResponseEntity<TaskResponse<List<DailyTask>>> getAllTasks(){

        // what is list of task is empty so take care of this case also

        List<DailyTask>  allTasks = taskService.getAll();
        if(allTasks != null && !allTasks.isEmpty()){
            return  ResponseEntity.status(HttpStatus.OK).body(new TaskResponse<>(allTasks,"All Task Fetched Successfully"));

        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new TaskResponse<>(null,"No Task Found"));



    }
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<TaskResponse<DailyTask>> updateTaskByID(@RequestBody DailyTask dailyTask, @PathVariable("id") Long id ){

        Optional<DailyTask> task  = taskService.getById(id);
        if(task.isPresent()){
            DailyTask oldTask =  task.get();
            oldTask.setTask(dailyTask.getTask());
            taskService.saveTask(oldTask);


            return ResponseEntity.status(HttpStatus.OK).body(new TaskResponse<>(oldTask,"Task Updated"));

        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new TaskResponse<>(null,"Task Not Found"));
        }


    }
    @DeleteMapping ("/deleteTask/{id}")
    public  ResponseEntity<TaskResponse<DailyTask>> deleteTaskByID(@PathVariable("id")Long id){

        Optional<DailyTask> task = taskService.getById(id);

        if(task.isPresent()){
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new TaskResponse<>(task.get(),"Task Deleted Succesfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TaskResponse<>(null,"Task Not Found"));
        }


    }


    @PostMapping("/saveTask")
    public ResponseEntity<TaskResponse<DailyTask>> createTask(@RequestBody DailyTask task){
       taskService.saveTask(task);
       return  ResponseEntity.status(HttpStatus.CREATED).body(new TaskResponse<>(task,"Task Created Successfully"));

    }

}
