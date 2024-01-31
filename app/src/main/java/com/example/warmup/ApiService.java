package com.example.warmup;

import com.example.warmup.model.AddTaskRequest;
import com.example.warmup.model.AddTaskResponse;
import com.example.warmup.model.GeneralResponse;
import com.example.warmup.model.LoginRequest;
import com.example.warmup.model.LoginResponse;
import com.example.warmup.model.RegisterRequest;
import com.example.warmup.model.RegisterResponse;
import com.example.warmup.model.ToDoListTableResponse;
import com.example.warmup.model.UpdateTaskRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("user/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("user/register/")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    //创建任务
    @POST("task")
    Call<AddTaskResponse> addTask(@Body AddTaskRequest addTaskRequest);

    //获取任务
    @GET("task")
    Call<ToDoListTableResponse> getTask(@Query("username") String username);

    //更新任务状态
    @PUT("task")
    Call<GeneralResponse> updateTask(@Body UpdateTaskRequest updateTaskRequest);

    @DELETE("task/{id}")
    Call<GeneralResponse> deleteTask(@Path("id") int id, @Query("username") String username);
}
