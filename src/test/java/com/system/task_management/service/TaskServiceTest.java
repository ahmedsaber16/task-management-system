package com.system.task_management.service;

import com.system.task_management.dto.TaskRes;
import com.system.task_management.exception.TaskNotFoundException;
import com.system.task_management.model.Task;
import com.system.task_management.model.TaskStatus;
import com.system.task_management.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepo taskRepo;


    @Test
    void testGetTaskById(){
        TaskService taskService = new TaskService(taskRepo,null);
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus(TaskStatus.TODO);

        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
        TaskRes result = taskService.getTaskById(1L);
        assertEquals(task.getTitle() ,result.getTitle());
    }

    @Test
    void testGetFindById_NotFound(){
        TaskService taskService = new TaskService(taskRepo,null);
        when(taskRepo.findById(999L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class ,()-> {
            taskService.getTaskById(999L);
        });
    }
}