package com.example.warmup.model;

import com.google.gson.annotations.SerializedName;

public class ToDoListTableResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("mse")
    private String mse;

    @SerializedName("data")
    private ToDoListTableData data;

    public ToDoListTableResponse(int code, String mse, ToDoListTableData data) {
        this.code = code;
        this.mse = mse;
        this.data = data;
    }

    public String getMse() {
        return mse;
    }

    public ToDoListTableData getData() {
        return data;
    }
}
