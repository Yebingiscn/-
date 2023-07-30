package com.example.service;

import com.example.entity.User;

public interface UserService {
    void getVerifyCode(String email);
    String toSign(User user);
    String toLogin(User user);
}
