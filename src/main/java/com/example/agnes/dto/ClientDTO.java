package com.example.agnes.dto;

import java.util.List;

public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private List<ProjectDTO> projects;

    // Constructors, getters, and setters
    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String email, List<ProjectDTO> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}
