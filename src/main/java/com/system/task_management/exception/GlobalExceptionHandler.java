package com.system.task_management.exception;


import com.system.task_management.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleTaskNotFound(TaskNotFoundException exception){
        ApiResponse<String> response = new ApiResponse<>(exception.getMessage() , null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND ).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFound(UserNotFoundException exception){
        ApiResponse<String> response = new ApiResponse<>(exception.getMessage() , null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidCredentials(InvalidCredentialException exception){
        ApiResponse<String> response = new ApiResponse<>(exception.getMessage() , null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

    }


}
