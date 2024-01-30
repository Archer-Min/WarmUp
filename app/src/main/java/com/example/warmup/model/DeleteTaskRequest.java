package com.example.warmup.model;

public class DeleteTaskRequest {
    private String username;
    private int id;

    public DeleteTaskRequest(String username, int id) {
        this.username = username;
        this.id = id;
    }
}
