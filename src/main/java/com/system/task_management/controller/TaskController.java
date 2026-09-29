package com.system.task_management.controller;

import com.system.task_management.dto.ApiResponse;
import com.system.task_management.dto.TaskReq;
import com.system.task_management.dto.TaskRes;
import com.system.task_management.model.Task;
import com.system.task_management.model.TaskStatus;
import com.system.task_management.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TaskRes>> createTask(@Valid @RequestBody TaskReq req){
        TaskRes res = taskService.createTask(req);
        ApiResponse<TaskRes> apiResponse = new ApiResponse<>("Task Created successfully" , res) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @GetMapping("/allTasks")
    public ResponseEntity<ApiResponse<List<TaskRes>>> getAllTasks(){
        List<TaskRes> res = taskService.getAllTasks();
        ApiResponse<List<TaskRes>> apiResponse = new ApiResponse<>("All Tasks" , res);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskRes>> getTaskById(@PathVariable Long id){
        TaskRes res = taskService.getTaskById(id);
        ApiResponse<TaskRes> apiResponse = new ApiResponse<>("Task Retrieved successfully" , res);
        return  ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TaskRes>> updateTask(@PathVariable Long id ,@Valid @RequestBody TaskReq req){
        TaskRes res = taskService.updateTask(req, id);
        ApiResponse<TaskRes> apiResponse = new ApiResponse<>("Task Updated Successfully" , res);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<TaskRes>>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<TaskRes> res = taskService.getTasksByStatus(status);
        ApiResponse<List<TaskRes>> apiResponse = new ApiResponse<>("Tasks Retrieved Successfully" , res);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/assign/{taskId}/{userId}")
    public ResponseEntity<ApiResponse<TaskRes>> assignTaskToUser(@PathVariable Long taskId , @PathVariable Long userId){
        TaskRes res = taskService.assignTaskToUser(taskId , userId);
        ApiResponse<TaskRes> apiResponse = new ApiResponse<>("Task assigned to user successfully" , res);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<List<TaskRes>>> getTasksByUser(@PathVariable Long id){
        List<TaskRes> res = taskService.getTasksByUser(id);
        ApiResponse<List<TaskRes>> apiResponse = new ApiResponse<>("Tasks Retrieved Successfully" , res);
        return ResponseEntity.ok(apiResponse);
    }
}
