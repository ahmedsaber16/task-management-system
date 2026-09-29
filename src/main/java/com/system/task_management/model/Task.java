package com.system.task_management.model;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String title;
    private  String description ;
    @Enumerated(EnumType.STRING)
    private  TaskStatus status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime dueDate;
    @ManyToOne
    private User assignedUser;



    public Task(){}

    public Task( String title, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime dueDate, User assignedUser) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.assignedUser = assignedUser;
    }


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}
