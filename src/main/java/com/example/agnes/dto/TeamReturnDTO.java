package com.example.agnes.dto;

import com.example.agnes.model.Project;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TeamReturnDTO {
    private UUID id;
    private String name;
    private List<Project> projects;
}
