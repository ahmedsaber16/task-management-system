package com.system.task_management.controller;

import com.system.task_management.dto.*;
import com.system.task_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginRes>> login(@RequestBody LoginReq req){
        LoginRes res = userService.login(req.getUserName(), req.getPassword());
        ApiResponse<LoginRes> apiResponse = new ApiResponse<>("Login successful", res);
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserRes>> createUser(@RequestBody UserReq req){
        UserRes res = userService.createUser(req);
        ApiResponse<UserRes> apiResponse = new ApiResponse<>("User created successfully" , res);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserRes>>> getAllUsers(){
        List<UserRes> res = userService.getAllUsers();
        ApiResponse<List<UserRes>> apiResponse = new ApiResponse<>("all users" , res);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("userId/{id}")
    public ResponseEntity<ApiResponse<UserRes>> getUserById(@PathVariable Long id){
        UserRes res = userService.getUserById(id);
        ApiResponse<UserRes> apiResponse = new ApiResponse<>("user by id" , res);
        return ResponseEntity.ok(apiResponse);
    }
}
