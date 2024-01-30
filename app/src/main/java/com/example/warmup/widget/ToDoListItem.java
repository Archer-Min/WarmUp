package com.example.warmup.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.warmup.R;

public class ToDoListItem {
    private View view; // 组件的根视图
    private TextView textView;

    public ToDoListItem(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.todo_container, null);

        // 在这里找到 TextView 并进行绑定
        textView = view.findViewById(R.id.todoText);
    }

    public void setItemText(String text) {
        // 在这里设置 TextView 的文本
        textView.setText(text);
    }

    public View getView() {
        return view;
    }
}
