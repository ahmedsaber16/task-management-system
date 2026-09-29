package com.system.task_management.dto;

import com.system.task_management.model.TaskStatus;
import java.time.LocalDateTime;

public class TaskRes {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private String assignedUserName;

    public TaskRes(Long id, String title, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime dueDate , String assignedUserName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.assignedUserName = assignedUserName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getAssignedUserName() {
        return assignedUserName;
    }
}