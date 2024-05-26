package com.example.agnes.dto;

public class CommentDTO {
    private Long id;
    private String text;
    private TaskDTO task;

    // Constructors, getters, and setters
    public CommentDTO() {
    }

    public CommentDTO(Long id, String text, TaskDTO task) {
        this.id = id;
        this.text = text;
        this.task = task;
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

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }
}
