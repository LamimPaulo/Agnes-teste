package com.example.agnes.controller;

import com.example.agnes.dto.TaskDTO;
import com.example.agnes.model.Task;
import com.example.agnes.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksByProjectId(@PathVariable Long projectId) {
        List<Task> tasks = taskService.findByProjectId(projectId);
        List<TaskDTO> taskDTOs = convertToDTOList(tasks);
        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/projects/tasks/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        Optional<Task> task = taskService.findById(taskId);
        assert task.orElse(null) != null;
        TaskDTO taskDTO = convertToDTO(task.orElse(null));
        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping("/{projectId}/tasks")
    public Task createTask(@PathVariable Long projectId, @RequestBody Task task) {
        return taskService.createTask(projectId, task);
    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task taskDetails) {
        Task task = taskService.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        return taskService.save(task);
    }

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
    }

    @PutMapping("/tasks/{taskId}/delegate")
    public Task delegateTask(@PathVariable Long taskId, @RequestParam Long workerId) {
        return taskService.delegateTask(taskId, workerId);
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        if (task.getAssignedWorker() != null) {
            taskDTO.setAssignedWorkerName(task.getAssignedWorker().getFirstName() + " " + task.getAssignedWorker().getLastName());
        } else {
            taskDTO.setAssignedWorkerName("Não atribuído");
        }
        return taskDTO;
    }

    private List<TaskDTO> convertToDTOList(List<Task> tasks) {
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            taskDTOs.add(convertToDTO(task));
        }
        return taskDTOs;
    }
}
