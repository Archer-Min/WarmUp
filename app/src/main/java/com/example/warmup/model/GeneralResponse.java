package com.example.warmup.model;

public class GeneralResponse {
    private int code;
    private String mse;

    public GeneralResponse(int code, String mse) {
        this.code = code;
        this.mse = mse;
    }

    public int getCode() {
        return code;
    }

    public String getMse() {
        return mse;
    }
}
