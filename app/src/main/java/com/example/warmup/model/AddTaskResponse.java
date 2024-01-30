package com.example.warmup.model;

public class AddTaskResponse {
    private String code;
    private String mse;

    public AddTaskResponse(String code, String mse) {
        this.code = code;
        this.mse = mse;
    }

    public String getCode() {
        return code;
    }

    public String getMse() {
        return mse;
    }
}
