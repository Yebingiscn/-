package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface AdminService {
    String toLoginAdmin(User user);
    List<User> showAdmin();
    List<User> showUserPublic();
    String addAdmin(User user);
    String delAdmin(User user);
}
