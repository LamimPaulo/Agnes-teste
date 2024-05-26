package com.example.agnes.repository;

import com.example.agnes.model.Client;
import com.example.agnes.model.Project;
import com.example.agnes.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface    ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByClientId(Long clientId);
}
