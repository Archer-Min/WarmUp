package com.example.warmup.model;

public class UpdateTaskRequest {
    private String username;
    private int id;

    public UpdateTaskRequest(String username, int id) {
        this.username = username;
        this.id = id;
    }
}
