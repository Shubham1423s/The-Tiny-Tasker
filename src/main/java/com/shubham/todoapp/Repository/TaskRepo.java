package com.shubham.todoapp.Repository;

import com.shubham.todoapp.Entity.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<DailyTask,Long> {
}
