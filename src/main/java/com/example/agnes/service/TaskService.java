package com.example.agnes.service;

import com.example.agnes.model.Project;
import com.example.agnes.model.Task;
import com.example.agnes.model.Worker;
import com.example.agnes.repository.ProjectRepository;
import com.example.agnes.repository.TaskRepository;
import com.example.agnes.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final WorkerRepository workerRepository;

    @Autowired
    public TaskService(
            TaskRepository taskRepository,
            ProjectRepository projectRepository,
            WorkerRepository workerRepository
    ) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.workerRepository = workerRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public Task createTask(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));
        task.setProject(project);
        return taskRepository.save(task);
    }

    public Task delegateTask(Long taskId, Long workerId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid worker ID"));
        task.setAssignedWorker(worker);
        return taskRepository.save(task);
    }
}
