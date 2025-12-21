package com.shubham.todoapp.Repository;

import com.shubham.todoapp.Entity.DailyTask;
import com.shubham.todoapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<DailyTask,Long> {
    List<DailyTask> findByUser(User user);
    DailyTask   deleteByTitle(String Title);
}
