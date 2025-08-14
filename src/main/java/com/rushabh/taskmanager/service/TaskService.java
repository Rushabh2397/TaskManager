package com.rushabh.taskmanager.service;

import com.rushabh.taskmanager.dto.TaskRequestDto;
import com.rushabh.taskmanager.dto.TaskResponseDto;
import com.rushabh.taskmanager.entity.Task;
import com.rushabh.taskmanager.enums.Priority;
import com.rushabh.taskmanager.enums.TaskStatus;
import com.rushabh.taskmanager.exception.TaskNotFoundException;
import com.rushabh.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public TaskResponseDto createTask(TaskRequestDto req) {
        Task newTask = new Task(
                req.getTitle(),
                req.getDescription(),
                TaskStatus.valueOf(req.getStatus().toUpperCase()),
                Priority.valueOf(req.getPriority().toUpperCase()),
                req.getDueDate()
        );

        Task savedTask = taskRepository.save(newTask);

        return new TaskResponseDto(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getPriority(),
                savedTask.getDueDate()
        );
    }

    public TaskResponseDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate()
        );
    }
}
