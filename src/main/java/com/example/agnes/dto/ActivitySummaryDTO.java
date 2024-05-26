package com.example.agnes.dto;

public class ActivitySummaryDTO {
    private Long id;
    private String description;

    // Constructors, getters, and setters
    public ActivitySummaryDTO() {
    }

    public ActivitySummaryDTO(Long id, String description) {
        this.id = id;
        this.description = description;
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
}
