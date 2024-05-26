package com.example.agnes.dto;

import com.example.agnes.enums.StatusProject;

public class ProjectSummaryDTO {
    private Long id;
    private String name;
    private StatusProject status;

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

    public StatusProject getStatus() {
        return status;
    }

    public void setStatus(StatusProject status) {
        this.status = status;
    }
}
