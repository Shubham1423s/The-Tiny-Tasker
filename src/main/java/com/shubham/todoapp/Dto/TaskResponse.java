package com.shubham.todoapp.Dto;

import com.shubham.todoapp.Entity.DailyTask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class TaskResponse<T> {

   private String message;
   private T data;
   // Encapsulation thing  we can not make them public otherwise any one change it
    // we have to provide getter and setter to give access to the user



   public TaskResponse(T data, String message){

        this.data= data;
       this.message = message;

    }
}
