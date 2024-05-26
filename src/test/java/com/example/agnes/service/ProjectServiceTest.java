package com.example.agnes.service;

import com.example.agnes.model.Client;
import com.example.agnes.model.Project;
import com.example.agnes.model.Team;
import com.example.agnes.model.Worker;
import com.example.agnes.repository.ClientRepository;
import com.example.agnes.repository.ProjectRepository;
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

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private Client client;
    private Team team;
    private Worker worker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setId(1L);
        client.setName("Test Client");

        worker = new Worker();
        worker.setId(1L);
        worker.setFirstName("Test Worker");

        team = new Team();
        team.setId(UUID.randomUUID());
        team.setWorkers(new ArrayList<>());
        team.getWorkers().add(worker);

        project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setClient(client);
        project.setTeams(new ArrayList<>());
        project.getTeams().add(team);
    }

    @Test
    void getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(project);

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void getAllByClientId() {
        List<Project> projects = new ArrayList<>();
        projects.add(project);

        when(projectRepository.findAllByClientId(1L)).thenReturn(projects);

        List<Project> result = projectService.getAllByClientId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(projectRepository, times(1)).findAllByClientId(1L);
    }

    @Test
    void getTeamMembers() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        List<Worker> result = projectService.getTeamMembers(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Worker", result.get(0).getFirstName());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void getProjectById() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> result = projectService.getProjectById(1L);
        assertTrue(result.isPresent());
        assertEquals("Test Project", result.get().getName());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(projectRepository.save(project)).thenReturn(project);

        Project result = projectService.save(project);
        assertNotNull(result);
        assertEquals("Test Project", result.getName());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void deleteById() {
        doNothing().when(projectRepository).deleteById(1L);

        projectService.deleteById(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }
}
