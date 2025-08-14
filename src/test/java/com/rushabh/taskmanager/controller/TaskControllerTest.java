package com.rushabh.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rushabh.taskmanager.dto.TaskRequestDto;
import com.rushabh.taskmanager.dto.TaskResponseDto;
import com.rushabh.taskmanager.enums.Priority;
import com.rushabh.taskmanager.enums.TaskStatus;
import com.rushabh.taskmanager.exception.TaskNotFoundException;
import com.rushabh.taskmanager.service.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc
public class TaskControllerTest {
    @MockBean
    private TaskService _taskService;


    @Autowired
    private MockMvc _mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Jackson for JSON

    private TaskResponseDto taskResponseDto;

    private TaskRequestDto taskRequestDto;


    @BeforeEach
    public void setup() {
        taskResponseDto = new TaskResponseDto(
                1L,
                "Complete Spring Boot Assignment",
                "Build a task management API",
                TaskStatus.PENDING,
                Priority.HIGH,
                LocalDate.now()
        );

        taskRequestDto = new TaskRequestDto(
                "Complete Spring Boot Assignment",
                "Build a task management API",
                TaskStatus.PENDING.toString(),
                Priority.HIGH.toString(),
                LocalDate.now()
        );
    }

    @Test
    public void createTaskSuccessfully() throws Exception {

        Mockito.when(_taskService.createTask(Mockito.any())).thenAnswer(inv -> taskResponseDto);
        _mockMvc.perform(post("/tasks")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(taskRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Complete Spring Boot Assignment"))
                .andExpect(jsonPath("$.description").value("Build a task management API"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.priority").value("HIGH"));

    }

    @Test
    public void createTaskInvalidReqInput() throws Exception {

        TaskRequestDto taskRequestInvalidDto = new TaskRequestDto(
                "Complete Spring Boot Assignment",
                "Build a task management API",
                "PENDINGSS",
                Priority.HIGH.toString(),
                LocalDate.now()
        );

        _mockMvc.perform(post("/tasks")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(taskRequestInvalidDto)))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void getTaskByIdNotFound() throws Exception {

         Mockito.when(_taskService.getTaskById(2L)).thenThrow(new TaskNotFoundException(2L));
        _mockMvc.perform(get("/tasks/2")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                )
                .andExpect(status().isNotFound());

    }

}
