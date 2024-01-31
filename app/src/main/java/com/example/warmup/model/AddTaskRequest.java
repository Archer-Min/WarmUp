package com.example.warmup.model;

public class AddTaskRequest {
    private String content;
    private String start_time;
    private String end_time;
    private String status;
    private String username;

    public AddTaskRequest(String content, String startTime, String endTime, String status, String username) {
        this.content = content;
        this.start_time = startTime;
        this.end_time = endTime;
        this.status = status;
        this.username = username;
    }
}
