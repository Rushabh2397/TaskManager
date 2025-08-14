package com.rushabh.taskmanager.dto;

import com.rushabh.taskmanager.enums.Priority;
import com.rushabh.taskmanager.enums.TaskStatus;
import com.rushabh.taskmanager.validations.ValidEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskRequestDto {

    @NotBlank
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Status is required")
    @ValidEnum(enumClass = TaskStatus.class, message = "Status must be one of: PENDING, IN_PROGRESS, COMPLETED, CANCELLED")
    private String status;

    @NotNull(message = "Priority is required")
    @ValidEnum(enumClass = Priority.class, message = "Priority must be one of: HIGH, MEDIUM, LOW")
    private String priority;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    public  TaskRequestDto(){}

    public TaskRequestDto(String title, String description, String status, String priority, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
