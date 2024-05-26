package com.example.agnes.service;

import com.example.agnes.model.Client;
import com.example.agnes.model.Project;
import com.example.agnes.model.Team;
import com.example.agnes.model.Worker;
import com.example.agnes.repository.ClientRepository;
import com.example.agnes.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public List<Project> getAllByClientId(Long clientId){
        return projectRepository.findAllByClientId(clientId);
    }

    public List<Worker> getTeamMembers(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        return project.getTeams().stream()
                .flatMap(team -> team.getWorkers().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public Optional<Project> getProjectById(Long id){
        return projectRepository.findById(id);
    }

    public Project save(Project project){
        return projectRepository.save(project);
    }

    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }
}
