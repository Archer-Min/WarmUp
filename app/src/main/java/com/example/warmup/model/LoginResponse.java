package com.example.warmup.model;

public class LoginResponse {
    private String msg;
    private int code;
    private String token;

    public LoginResponse(String msg, int code, String token) {
        this.msg = msg;
        this.code = code;
        this.token = token;
    }

    public int getCode() {
        return code;
    }
}
