package com.example.agnes.controller;

import com.example.agnes.dto.TeamDTO;
import com.example.agnes.dto.TeamReturnDTO;
import com.example.agnes.model.Team;
import com.example.agnes.service.TeamService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    public Optional<Team> getTeamById(@PathVariable UUID id) {
        return teamService.getById(id);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.save(team);
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable UUID id, @RequestBody Team team) {
        team.setId(id);
        return teamService.save(team);
    }

    @PutMapping("/{id}/projects")
    public Team updateTeamProjects(@PathVariable UUID id, @RequestBody List<Long> projectIds) {
        return teamService.updateTeamProjects(id, projectIds);
    }

    @PutMapping("/{id}/workers")
    public Team updateTeamWorkers(@PathVariable UUID id, @RequestBody List<Long> workersIds) {
        return teamService.updateTeamWorkers(id, workersIds);
    }


    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable UUID id) {
        teamService.deleteById(id);
    }

    private List<TeamReturnDTO> mapToDTOs(List<Team> teams) {
        List<TeamReturnDTO> dtos = new ArrayList<>();
        for (Team team : teams) {
            TeamReturnDTO dto = new TeamReturnDTO();
            dto.setId(team.getId());
            dto.setName(team.getName());
//            dto.setProjects(team.getProjects());
            dtos.add(dto);
        }
        return dtos;
    }
}
