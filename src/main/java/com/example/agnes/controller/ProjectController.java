package com.example.agnes.controller;

import com.example.agnes.dto.ProjectDTO;
import com.example.agnes.dto.StatusProjectDTO;
import com.example.agnes.model.Client;
import com.example.agnes.model.Project;
import com.example.agnes.enums.StatusProject;
import com.example.agnes.model.Worker;
import com.example.agnes.service.ClientService;
import com.example.agnes.service.ProjectService;
import com.example.agnes.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ClientService clientService;

    @Autowired
    public ProjectController(ProjectService projectService, ClientService clientService) {
        this.projectService = projectService;
        this.clientService = clientService;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return projects.stream()
                .map(DTOConverter::toProjectDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(project -> ResponseEntity.ok(DTOConverter.toProjectDTO(project)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        Project savedProject = projectService.save(project);
        return ResponseEntity.ok(DTOConverter.toProjectDTO(savedProject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        if (!projectService.getProjectById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Project project = convertToEntity(projectDTO);
        project.setId(id);
        Project updatedProject = projectService.save(project);
        return ResponseEntity.ok(DTOConverter.toProjectDTO(updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (!projectService.getProjectById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listStatus")
    public List<StatusProjectDTO> getAllStatusProject() {
        return List.of(StatusProject.values())
                .stream()
                .map(status -> new StatusProjectDTO(status.name(), status.getDescription()))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{projectId}/team")
    public List<Worker> getTeamMembers(@PathVariable Long projectId) {
        return projectService.getTeamMembers(projectId);
    }

    private Project convertToEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setStatus(projectDTO.getStatus());

        if (projectDTO.getClientId() != null) {
            Client client = clientService.getClientById(projectDTO.getClientId());
            project.setClient(client);
        }

        return project;
    }
}
