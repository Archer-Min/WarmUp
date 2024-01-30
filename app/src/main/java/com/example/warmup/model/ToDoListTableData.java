package com.example.warmup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToDoListTableData {
    @SerializedName("item")
    private List<ToDoItem> items;

    @SerializedName("total")
    private int total;

    public ToDoListTableData(List<ToDoItem> items, int total) {
        this.items = items;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public List<ToDoItem> getItems() {
        return items;
    }

    public void setItems(List<ToDoItem> items) {
        this.items = items;
    }
}
