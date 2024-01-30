package com.example.warmup;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.warmup.databinding.FragmentToDoListBinding;
import com.example.warmup.model.AddTaskRequest;
import com.example.warmup.model.AddTaskResponse;
import com.example.warmup.widget.ToDoListItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoListFragment extends Fragment {
    ApiService apiService = ApiClient.getApiService();
    ToDoListViewModel viewModel;
    private List<ToDoListItem> itemList = new ArrayList<>();
    private ViewGroup container;
    String toDoContent;

    public ToDoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentToDoListBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_to_do_list,container,false);

        viewModel = new ViewModelProvider(this).get(ToDoListViewModel.class);
        binding.setTodolistviewmodel(viewModel);
        binding.setLifecycleOwner(this);

        this.container = binding.itemContainer;

        binding.addTodoBt.setOnClickListener(view -> {
            toDoContent = binding.todoInput.getText().toString();
            viewModel.setItem(toDoContent);
            addToDoItem(toDoContent);

            AddTaskRequest addTaskRequest = new AddTaskRequest(toDoContent,"20220101","20221109","0","qwe");
            Call<AddTaskResponse> call = apiService.addTask(addTaskRequest);
            call.enqueue(new Callback<AddTaskResponse>() {
                @Override
                public void onResponse(Call<AddTaskResponse> call, Response<AddTaskResponse> response) {
                    if(response.isSuccessful()){
                        AddTaskResponse addTaskResponse = response.body();
                        Toast.makeText(getContext(),addTaskResponse.getMse(),Toast.LENGTH_SHORT).show();
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

    private void addToDoItem(String toDoContent){
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