package com.example.agnes.dto;

import com.example.agnes.enums.StatusProject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private StatusProject status;
    private Long clientId;
    private ClientDTO client;
}
