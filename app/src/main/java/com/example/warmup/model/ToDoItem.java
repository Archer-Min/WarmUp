package com.example.warmup.model;

import com.google.gson.annotations.SerializedName;

public class ToDoItem {
    @SerializedName("id")
    private int id;

    @SerializedName("content")
    private String content;

    @SerializedName("status")
    private String status;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("end_time")
    private String endTime;

    public ToDoItem(int id, String content, String status, String startTime, String endTime) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
