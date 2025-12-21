package com.shubham.todoapp.Service;

import com.shubham.todoapp.Repository.UserRepo;
import org.hibernate.annotations.Source;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @BeforeAll
    static  void start(){
    System.out.println("Test Started");
}

    @AfterEach
    void cleanUp(){
        System.out.println("Test Finished");
    }


    @Test
    @Disabled
    public  void testValue(){
        assertEquals(6,9-3);


    }
    @Test
    public  void testFindByFirstName(){
        assertNotNull(userRepo.findByFirstName("ram1"));
    }
    @ParameterizedTest
    @ValueSource (strings = {
            "ram",
            "ram1",
            "shubham",
            "shyam"
    })
    public void testfindByUserName(String  userName){
        assertNotNull(userRepo.findByFirstName(userName));
    }
}

