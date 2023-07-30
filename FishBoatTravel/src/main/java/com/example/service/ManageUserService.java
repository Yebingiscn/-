package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.UserPub;

import java.util.List;

public interface ManageUserService {
    IPage<UserPub> getAllUser(int current_page, int total);

    IPage<UserPub> selectByUserName(String user_name, int current_page, int total);

    String blockUser(String user_name);

    String unBlockUser(String user_name);

    long getUserCount();

    String isAddInfo(String user_name);

    String addInfo(UserPub userPub);

    String getMail(String user_name);

    String changePwd(UserPub userPub);

    String updateInfo(UserPub userPub);

    List<UserPub> getUserInfo(String user_name);
}
