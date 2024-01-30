package com.example.warmup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.warmup.databinding.FragmentLoginBinding;
import com.example.warmup.databinding.FragmentRegisterBinding;
import com.example.warmup.model.LoginRequest;
import com.example.warmup.model.LoginResponse;
import com.example.warmup.model.RegisterRequest;
import com.example.warmup.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    private AuthViewModel viewModel;
    ApiService apiService = ApiClient.getApiService();

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRegisterBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.textView3.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment));

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        binding.registerBt.setOnClickListener(v -> {
            String username = binding.editTextName.getText().toString();
            String password = binding.editTextPassword.getText().toString();

            RegisterRequest registerRequest = new RegisterRequest(username, password);
            // 发起注册请求
            Call<RegisterResponse> call = apiService.register(registerRequest);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful()) {
                        // 登录成功，处理响应
                        RegisterResponse registerResponse = response.body();
                        if (response.code() == 200) {
                            Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment);
                        } else {
                            Toast.makeText(getContext(),"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("re", "Error: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Log.e("RequestFailure", "Failed to make the request: " + t.getMessage(), t);
                }
            });
        });
        return binding.getRoot();
    }
}