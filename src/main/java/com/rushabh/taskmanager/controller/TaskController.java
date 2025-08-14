package com.rushabh.taskmanager.controller;

import com.rushabh.taskmanager.dto.TaskResponseDto;
import com.rushabh.taskmanager.entity.Task;
import com.rushabh.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rushabh.taskmanager.dto.TaskRequestDto;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto newTask = taskService.createTask(taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long taskId) {
        TaskResponseDto newTask = taskService.getTaskById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(newTask);
    }
}
