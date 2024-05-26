package com.example.agnes.dto;

import lombok.Data;

import java.util.List;

@Data
public class WorkerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private List<TeamDTO> teams;

}
