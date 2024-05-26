package com.example.agnes.service;

import com.example.agnes.model.Project;
import com.example.agnes.model.Task;
import com.example.agnes.model.Worker;
import com.example.agnes.repository.ProjectRepository;
import com.example.agnes.repository.TaskRepository;
import com.example.agnes.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private Project project;
    private Worker worker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project = new Project();
        project.setId(1L);
        project.setName("Test Project");

        worker = new Worker();
        worker.setId(1L);
        worker.setFirstName("Test Worker");

        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setProject(project);
    }

    @Test
    void findAll() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Test Task", result.get().getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.save(task);
        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void delete() {
        doNothing().when(taskRepository).deleteById(1L);

        taskService.delete(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void findByProjectId() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        when(taskRepository.findByProjectId(1L)).thenReturn(tasks);

        List<Task> result = taskService.findByProjectId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(taskRepository, times(1)).findByProjectId(1L);
    }

    @Test
    void createTask() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(1L, task);
        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        verify(projectRepository, times(1)).findById(1L);
    }
}
