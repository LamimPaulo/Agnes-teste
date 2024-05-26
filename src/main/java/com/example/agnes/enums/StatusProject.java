package com.example.agnes.enums;

public enum StatusProject {
    DONE("Pronto"),
    PAUSED("Pausado"),
    PENDING("Pendente"),
    ON_TRACK("No ritmo"),
    AT_RISK("Em risco"),
    OFF_TRACK("Fora de ritmo");

    private final String description;

    StatusProject(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static StatusProject[] getAllStatus() {
        return StatusProject.values();
    }
}
