package com.example.agnes.dto;

import com.example.agnes.enums.StatusProject;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectRequestDTO {
    private Long id;
    private String name;

    private Long clientId;

    @Enumerated(EnumType.STRING)
    private StatusProject status;
}
