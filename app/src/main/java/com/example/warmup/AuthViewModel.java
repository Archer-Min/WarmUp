package com.example.warmup;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class AuthViewModel extends ViewModel {
    private Map<String, String> registeredUsers = new HashMap<>();

    public boolean register(String username, String password) {
        if (!registeredUsers.containsKey(username)) {
            registeredUsers.put(username, password);
            return true;
        } else {
            return false; // 用户名已存在，注册失败
        }
    }

    public boolean login(String username, String password) {
        return registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password);
    }
}
