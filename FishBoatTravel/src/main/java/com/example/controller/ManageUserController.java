package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.UserPub;
import com.example.service.ManageUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage-user")
public class ManageUserController {
    @Resource
    ManageUserService manageUserService;

    @RequestMapping("/get-all-user")
    public IPage<UserPub> getAllUser(@RequestParam(value = "current_page") int current_page,
                                     @RequestParam(value = "total_page") int total_page) {
        return manageUserService.getAllUser(current_page, total_page);
    }

    @RequestMapping("/search-user")
    public IPage<UserPub> selectByUserName(@RequestParam(value = "user_name") String user_name,
                                           @RequestParam(value = "current_page") int current_page,
                                           @RequestParam(value = "total_page") int total_page) {
        return manageUserService.selectByUserName(user_name, current_page, total_page);
    }

    @RequestMapping("/block-user")
    public String blockUser(@RequestParam(value = "user_name")String user_name) {
        return manageUserService.blockUser(user_name);
    }

    @RequestMapping("/unblock-user")
    public String unBlockUser(@RequestParam(value = "user_name")String user_name) {
        return manageUserService.unBlockUser(user_name);
    }

    @RequestMapping("/is-add-info")
    public String isAddInfo(@RequestParam(value = "user_name")String user_name) {
        return manageUserService.isAddInfo(user_name);
    }

    @RequestMapping("/add-info")
    public String addInfo(@RequestBody UserPub userPub) {
        return manageUserService.addInfo(userPub);
    }

    @RequestMapping("/get-mail")
    public String getMail(@RequestParam(value = "user_name") String user_name) {
        return manageUserService.getMail(user_name);
    }

    @RequestMapping("/update-info")
    public String updateInfo(@RequestBody UserPub userPub) {
        return manageUserService.updateInfo(userPub);
    }

    @RequestMapping("/update-pwd")
    public String updatePwd(@RequestBody UserPub userPub) {
        return manageUserService.changePwd(userPub);
    }

    @RequestMapping("/get-user-info")
    public List<UserPub> getUserByUserName(@RequestParam(value = "user_name")String user_name) {
        return manageUserService.getUserInfo(user_name);
    }
}
