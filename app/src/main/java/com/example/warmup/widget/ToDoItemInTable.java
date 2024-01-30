package com.example.warmup.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.warmup.R;

public class ToDoItemInTable {
    private View view; // 组件的根视图
    private TextView taskText;
    private TextView statusText;

    public ToDoItemInTable(Context context,String task,String status){
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.todo_container_in_table,null);
        taskText = view.findViewById(R.id.task);
        statusText = view.findViewById(R.id.status);
        taskText.setText(task);
        statusText.setText(status);
    }

    public View getView() {
        return view;
    }
}
