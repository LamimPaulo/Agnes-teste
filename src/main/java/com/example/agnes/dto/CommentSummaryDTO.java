package com.example.agnes.dto;

public class CommentSummaryDTO {
    private Long id;
    private String text;

    // Constructors, getters, and setters
    public CommentSummaryDTO() {
    }

    public CommentSummaryDTO(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
