package com.example.warmup.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;

import com.example.warmup.ApiClient;
import com.example.warmup.ApiService;
import com.example.warmup.databinding.TaskDetailDialogBinding;
import com.example.warmup.model.GeneralResponse;
import com.example.warmup.model.UpdateTaskRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailDialog {
    ApiService apiService = ApiClient.getApiService();
    private TaskDetailDialogBinding binding;

    public TaskDetailDialog(Context context, String taskText, String statusText, String startTime, String endTime, String statusBt, int index) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = TaskDetailDialogBinding.inflate(inflater);
        binding.setLifecycleOwner((LifecycleOwner) context);
        // 设置数据
        binding.taskContent.setText(taskText);
        binding.taskStatus.setText(statusText);
        binding.taskStTime.setText(startTime);
        binding.taskEndTime.setText(endTime);
        binding.setStatusBt.setText(statusBt);
        binding.setStatusBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest("qwe", index);
                Call<GeneralResponse> call1 = apiService.updateTask(updateTaskRequest);
                call1.enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.body().getCode() == 200) {
                            Toast.makeText(context, "更新成功", Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        //删除任务
        binding.deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GeneralResponse> call2 = apiService.deleteTask(index, "qwe");
                call2.enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getCode() == 200) {
                                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("E", "error");
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    public View getView() {
        return binding.getRoot();
    }
}
