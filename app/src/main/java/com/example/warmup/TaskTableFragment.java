package com.example.warmup;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.warmup.databinding.FragmentTaskTableBinding;
import com.example.warmup.model.ToDoItem;
import com.example.warmup.model.ToDoListTableResponse;
import com.example.warmup.widget.TaskDetailDialog;
import com.example.warmup.widget.ToDoItemInTable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaskTableFragment extends Fragment {
    ApiService apiService = ApiClient.getApiService();
    int total;
    private ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTaskTableBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_table, container, false);
        this.container = binding.taskItemContainer;

        Call<ToDoListTableResponse> call = apiService.getTask("qwe");
        call.enqueue(new Callback<ToDoListTableResponse>() {
            @Override
            public void onResponse(Call<ToDoListTableResponse> call, Response<ToDoListTableResponse> response) {
                if (response.isSuccessful()) {
                    total = response.body().getData().getTotal();
                    List<ToDoItem> items = response.body().getData().getItems();
                    for (int index = 0; index < total; index++) {
                        ToDoItem item = items.get(index);
                        addTaskItem(item.getContent(), getStatusText(item.getStatus()), index);
                    }
                }
            }

            @Override
            public void onFailure(Call<ToDoListTableResponse> call, Throwable t) {

            }
        });
        return binding.getRoot();
    }

    private void addTaskItem(String task, String status, int index) {
        ToDoItemInTable toDoItemInTable = new ToDoItemInTable(getContext(), task, status);
        toDoItemInTable.getView().setOnClickListener(view -> {
            Call<ToDoListTableResponse> call = apiService.getTask("qwe");
            call.enqueue(new Callback<ToDoListTableResponse>() {
                @Override
                public void onResponse(Call<ToDoListTableResponse> call, Response<ToDoListTableResponse> response) {
                    if (response.isSuccessful()) {
                        List<ToDoItem> items = response.body().getData().getItems();
                        ToDoItem item = items.get(index);
                        showTaskDetailDialog(getContext(), item.getContent(), getStatusText(item.getStatus()), item.getStartTime(), item.getEndTime(), getStatusBt(item.getStatus()), index + 1);
                    }
                }

                @Override
                public void onFailure(Call<ToDoListTableResponse> call, Throwable t) {

                }
            });
        });
        container.addView(toDoItemInTable.getView());
    }

    private String getStatusText(String status) {
        return (status.equals("0")) ? "未完成" : "已完成";
    }

    private String getStatusBt(String status) {
        return (status.equals("0")) ? "确认已完成" : "改为未完成";
    }

    private void showTaskDetailDialog(Context context, String taskText, String statusText, String startTime, String endTime, String statusBt, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("任务详情");

        TaskDetailDialog taskDetailDialog = new TaskDetailDialog(context, taskText, statusText, startTime, endTime, statusBt, index);

        builder.setView(taskDetailDialog.getView())
                .setPositiveButton("确定", (dialog, id) -> dialog.dismiss());

        builder.create().show();
    }
}