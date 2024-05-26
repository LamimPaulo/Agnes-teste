package com.example.agnes.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize
@JsonDeserialize
@Setter
@Getter
public class StatusProjectDTO {
    private String value;
    private String description;

    public StatusProjectDTO(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
