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

import com.example.warmup.databinding.FragmentRegisterBinding;
import com.example.warmup.model.RegisterRequest;
import com.example.warmup.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    ApiService apiService = ApiClient.getApiService();

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRegisterBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.textView3.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment));
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
                        RegisterResponse registerResponse = response.body();
                        if (registerResponse.getCode() == 200) {
                            Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment);
                        } else {
                            Toast.makeText(getContext(), "注册失败", Toast.LENGTH_SHORT).show();
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