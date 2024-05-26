package com.example.agnes.service;

import com.example.agnes.model.Project;
import com.example.agnes.model.Team;
import com.example.agnes.model.Worker;
import com.example.agnes.repository.ProjectRepository;
import com.example.agnes.repository.TeamRepository;
import com.example.agnes.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private TeamService teamService;

    private Team team;
    private Project project;
    private Worker worker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        worker = new Worker();
        worker.setId(1L);
        worker.setFirstName("Test Worker");

        project = new Project();
        project.setId(1L);
        project.setName("Test Project");

        team = new Team();
        team.setId(UUID.randomUUID());
        team.setName("Test Team");
        team.setWorkers(new ArrayList<>());
        team.getWorkers().add(worker);
    }

    @Test
    void getAll() {
        List<Team> teams = new ArrayList<>();
        teams.add(team);

        when(teamRepository.findAll()).thenReturn(teams);

        List<Team> result = teamService.getAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        when(teamRepository.findById(team.getId())).thenReturn(Optional.of(team));

        Optional<Team> result = teamService.getById(team.getId());
        assertTrue(result.isPresent());
        assertEquals("Test Team", result.get().getName());
        verify(teamRepository, times(1)).findById(team.getId());
    }

    @Test
    void save() {
        when(teamRepository.save(team)).thenReturn(team);

        Team result = teamService.save(team);
        assertNotNull(result);
        assertEquals("Test Team", result.getName());
        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void updateTeamProjects() {
        List<Long> projectIds = new ArrayList<>();
        projectIds.add(project.getId());

        when(teamRepository.findById(team.getId())).thenReturn(Optional.of(team));
        when(projectRepository.findAllById(projectIds)).thenReturn(List.of(project));
        when(teamRepository.save(team)).thenReturn(team);

        Team result = teamService.updateTeamProjects(team.getId(), projectIds);
        assertNotNull(result);
        assertEquals(1, result.getProjects().size());
        verify(teamRepository, times(1)).findById(team.getId());
        verify(projectRepository, times(1)).findAllById(projectIds);
        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void updateTeamWorkers() {
        List<Long> workerIds = new ArrayList<>();
        workerIds.add(worker.getId());

        when(teamRepository.findById(team.getId())).thenReturn(Optional.of(team));
        when(workerRepository.findAllById(workerIds)).thenReturn(List.of(worker));
        when(teamRepository.save(team)).thenReturn(team);

        Team result = teamService.updateTeamWorkers(team.getId(), workerIds);
        assertNotNull(result);
        assertEquals(1, result.getWorkers().size());
        verify(teamRepository, times(1)).findById(team.getId());
        verify(workerRepository, times(1)).findAllById(workerIds);
        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void deleteById() {
        doNothing().when(teamRepository).deleteById(team.getId());

        teamService.deleteById(team.getId());
        verify(teamRepository, times(1)).deleteById(team.getId());
    }
}
