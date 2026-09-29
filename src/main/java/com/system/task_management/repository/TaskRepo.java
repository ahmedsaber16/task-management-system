package com.system.task_management.repository;

import com.system.task_management.model.Task;
import com.system.task_management.model.TaskStatus;
import com.system.task_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task , Long> {

    List<Task> findByStatus(TaskStatus status);
    List<Task> findByAssignedUser(User user);
}
