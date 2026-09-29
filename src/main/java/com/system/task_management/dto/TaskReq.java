package com.system.task_management.dto;

import com.system.task_management.model.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskReq    {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private  String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    public TaskReq() {}

    public TaskReq(String title , String description, TaskStatus status , LocalDateTime dueDate){
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }


    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }





}
