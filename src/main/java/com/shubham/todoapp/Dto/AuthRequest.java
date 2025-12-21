package com.shubham.todoapp.Dto;

import com.shubham.todoapp.Enums.Priority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String userName;
    private String password;
}
