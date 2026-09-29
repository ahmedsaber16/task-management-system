package com.system.task_management.service;

import com.system.task_management.dto.TaskReq;
import com.system.task_management.dto.TaskRes;
import com.system.task_management.dto.UserReq;
import com.system.task_management.exception.TaskNotFoundException;
import com.system.task_management.exception.UserNotFoundException;
import com.system.task_management.model.Task;
import com.system.task_management.model.TaskStatus;
import com.system.task_management.model.User;
import com.system.task_management.repository.TaskRepo;
import com.system.task_management.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private TaskRepo taskRepo;
    private UserRepo userRepo;

    public TaskService (TaskRepo taskRepo , UserRepo userRepo){
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public List<TaskRes> getAllTasks(){
        List<Task> taskList = taskRepo.findAll();
        return taskList.stream().map(this::convertToResponse).toList();
    }

    public TaskRes createTask(TaskReq req){
        Task task = convertToEntity(req);
        Task savedTask = taskRepo.save(task);
        return convertToResponse(savedTask);
    }

    public TaskRes getTaskById(Long id){
        Task task= taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("task not found with id" + id));
        return convertToResponse(task);
    }


    public TaskRes updateTask(TaskReq updatedTask , Long id){
       Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDueDate(updatedTask.getDueDate());
        Task savedTask = taskRepo.save(existingTask);
        return convertToResponse(savedTask);
    }

    public String deleteTask(Long id){
        Task taskFound = taskRepo.findById(id)
                .orElseThrow(() ->new TaskNotFoundException("task not found"));

        taskRepo.delete(taskFound);
        return "task deleted successfully";
    }

    public List<TaskRes> getTasksByStatus(TaskStatus status){
        List<Task> taskList = taskRepo.findByStatus(status);
        return taskList.stream().map(this::convertToResponse).toList();
    }

    public TaskRes assignTaskToUser(Long taskId , Long userId){
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("taskNotFound"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("userNotFound"));

        task.setAssignedUser(user);
        Task savedTask = taskRepo.save(task);
        return convertToResponse(savedTask);
    }

    public List<TaskRes> getTasksByUser(Long userId){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("userNotFound"));
        List<Task> tasks = taskRepo.findByAssignedUser(user);
        return tasks.stream().map(this::convertToResponse).toList();

    }



    public Task convertToEntity(TaskReq req){
        Task task = new Task();
        task.setTitle(req.getTitle());
        task.setStatus(req.getStatus());
        task.setDescription(req.getDescription());
        task.setCreatedAt(LocalDateTime.now());
        task.setDueDate(req.getDueDate());
        return task;
    }

    public TaskRes convertToResponse(Task task){
        String assignedUser = (task.getAssignedUser() != null) ? task.getAssignedUser().getUserName():"unassigned";
        return new TaskRes(task.getId() , task.getTitle() ,task.getDescription() ,task.getStatus() , task.getCreatedAt() , task.getDueDate() , assignedUser);
    }




}
