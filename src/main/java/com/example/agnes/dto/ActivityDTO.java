package com.example.agnes.dto;

import com.example.agnes.model.Project;

public class ActivityDTO {
    private Long id;
    private String description;
    private ProjectDTO project;

    // Constructors, getters, and setters
    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String description, ProjectDTO project) {
        this.id = id;
        this.description = description;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }
}
