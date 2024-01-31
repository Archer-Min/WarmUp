package com.example.warmup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.warmup.databinding.FragmentToDoListBinding;
import com.example.warmup.model.AddTaskRequest;
import com.example.warmup.model.AddTaskResponse;
import com.example.warmup.model.ToDoItem;
import com.example.warmup.model.ToDoListTableResponse;
import com.example.warmup.widget.ToDoListItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoListFragment extends Fragment {
    ApiService apiService = ApiClient.getApiService();
    ToDoListViewModel viewModel;
    private List<ToDoListItem> itemList = new ArrayList<>();
    private ViewGroup container;
    String toDoContent;
    String formattedDate;

    public ToDoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentToDoListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_to_do_list, container, false);

        viewModel = new ViewModelProvider(this).get(ToDoListViewModel.class);
        binding.setTodolistviewmodel(viewModel);
        binding.setLifecycleOwner(this);

        this.container = binding.itemContainer;

        Call<ToDoListTableResponse> call0 = apiService.getTask("qwe");
        call0.enqueue(new Callback<ToDoListTableResponse>() {
            @Override
            public void onResponse(Call<ToDoListTableResponse> call, Response<ToDoListTableResponse> response) {
                if (response.isSuccessful()) {
                    List<ToDoItem> items = response.body().getData().getItems();
                    for (int index = 0; index < items.size(); index++) {
                        ToDoItem item = items.get(index);
                        addToDoItem(item.getContent());
                    }
                }
            }

            @Override
            public void onFailure(Call<ToDoListTableResponse> call, Throwable t) {

            }
        });

        binding.addTodoBt.setOnClickListener(view -> {
            toDoContent = binding.todoInput.getText().toString();
            viewModel.setItem(toDoContent);
            addToDoItem(toDoContent);

            long timestamp = binding.calendarView.getDate();
            Date date = new Date(timestamp);
            // 使用 SimpleDateFormat 格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            formattedDate = sdf.format(date);
            Log.d("date",formattedDate);

            AddTaskRequest addTaskRequest = new AddTaskRequest(toDoContent, formattedDate, "20301109", "0", "qwe");
            Call<AddTaskResponse> call = apiService.addTask(addTaskRequest);
            call.enqueue(new Callback<AddTaskResponse>() {
                @Override
                public void onResponse(Call<AddTaskResponse> call, Response<AddTaskResponse> response) {
                    if (response.isSuccessful()) {
                        AddTaskResponse addTaskResponse = response.body();
                        Toast.makeText(getContext(), addTaskResponse.getMse(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddTaskResponse> call, Throwable t) {
                    Log.e("RequestFailure", "Failed to make the request: " + t.getMessage(), t);
                }
            });
        });

        binding.tableBt.setOnClickListener(view -> {
            Navigation.findNavController(getView()).navigate(R.id.action_toDoListFragment_to_taskTableFragment);
        });
        return binding.getRoot();
    }

    private void addToDoItem(String toDoContent) {
        ToDoListItem toDoListItem = new ToDoListItem(getContext());
        toDoListItem.setItemText(toDoContent);
        itemList.add(toDoListItem);
        container.addView(toDoListItem.getView());
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.save();
    }
}
