package com.example.warmup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.warmup.databinding.FragmentLoginBinding;
import com.example.warmup.model.LoginRequest;
import com.example.warmup.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    ApiService apiService = ApiClient.getApiService();

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.textView2.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment));
        binding.setLifecycleOwner(this);
        binding.loginBt.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_toDoListFragment));
        binding.loginBt.setOnClickListener(v -> {
            String username = binding.editTextName.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            // 创建登录请求体
            LoginRequest loginRequest = new LoginRequest(username, password);
            // 发起登录请求
            Call<LoginResponse> call = apiService.login(loginRequest);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        // 登录成功，处理响应
                        LoginResponse loginResponse = response.body();
                        if (loginResponse.getCode() == 200) {
                            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_toDoListFragment);
                        } else {
                            Toast.makeText(getContext(), "登录失败，请检查账号密码", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("re", "Error: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("RequestFailure", "Failed to make the request: " + t.getMessage(), t);
                }
            });

        });
        return binding.getRoot();
    }
}

//    // 登录请求
//    LoginRequest loginRequest = new LoginRequest();
//loginRequest.setUsername("your_username");
//        loginRequest.setPassword("your_password");
//
//        Call<LoginResponse> loginCall = apiService.login(loginRequest);
//        loginCall.enqueue(new Callback<LoginResponse>() {
//@Override
//public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//        if (response.isSuccessful()) {
//        // 登录成功，处理响应
//        LoginResponse loginResponse = response.body();
//        String token = loginResponse.getToken();
//        } else {
//        // 登录失败，处理错误
//        // ...
//        }
//        }
//
//@Override
//public void onFailure(Call<LoginResponse> call, Throwable t) {
//        // 网络请求失败，处理错误
//        // ...
//        }
//        });
