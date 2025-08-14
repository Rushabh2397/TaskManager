package com.rushabh.taskmanager.repository;

import com.rushabh.taskmanager.entity.Task;
import com.rushabh.taskmanager.enums.Priority;
import com.rushabh.taskmanager.enums.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository repository;

    @Test
    void saveTask(){
        Task task = new Task(
                "Complete Spring Boot Assignment",
                "Build a task management API",
                TaskStatus.PENDING,
                Priority.HIGH,
                LocalDate.now()
        );
        repository.save(task);
        Page<Task> page = repository.findAll(PageRequest.of(0, 10));
        assertThat(page.getTotalElements()).isGreaterThan(0);

    }
}
