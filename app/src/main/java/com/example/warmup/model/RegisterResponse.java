package com.example.warmup.model;

public class RegisterResponse {
    private String msg;
    private int code;

    public RegisterResponse(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
