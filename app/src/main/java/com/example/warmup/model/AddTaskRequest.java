package com.example.warmup.model;

public class AddTaskRequest {
    private String content;
    private String startTime;
    private String endTime;
    private String status;
    private String username;

    public AddTaskRequest(String content, String startTime, String endTime, String status, String username) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.username = username;
    }
}
