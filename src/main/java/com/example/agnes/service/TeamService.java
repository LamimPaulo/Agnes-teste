package com.example.agnes.service;

import com.example.agnes.dto.TeamDTO;
import com.example.agnes.model.Project;
import com.example.agnes.model.Team;
import com.example.agnes.model.Worker;
import com.example.agnes.repository.ProjectRepository;
import com.example.agnes.repository.TeamRepository;
import com.example.agnes.repository.WorkerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final ProjectRepository projectRepository;
    private final WorkerRepository workerRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, ProjectRepository projectRepository, WorkerRepository workerRepository) {
        this.teamRepository = teamRepository;
        this.projectRepository = projectRepository;
        this.workerRepository = workerRepository;
    }

    public List<Team> getAll(){
        return teamRepository.findAll();
    }

    public Optional<Team> getById(UUID id){
        return teamRepository.findById(id);
    }

    public Team save(Team team){
        return teamRepository.save(team);
    }


    @Transactional
    public Team updateTeamProjects(UUID teamId, List<Long> projectIds) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Invalid team ID"));
        List<Project> projects = projectRepository.findAllById(projectIds);
        team.setProjects(projects);
        return teamRepository.save(team);
    }

    @Transactional
    public Team updateTeamWorkers(UUID teamId, List<Long> workersIds) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Invalid team ID"));
        List<Worker> workers = workerRepository.findAllById(workersIds);
        team.setWorkers(workers);
        return teamRepository.save(team);
    }

    public void deleteById(UUID id){
        teamRepository.deleteById(id);
    }
}
