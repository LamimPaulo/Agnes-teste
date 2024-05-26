package com.example.agnes.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TeamDTO {
    private UUID id;
    private String name;
    private List<Long> projectIds;
    private List<Long> workersIds;

}
