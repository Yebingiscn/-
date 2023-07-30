package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {
    @Resource
    UserService userService;
    @RequestMapping("/get-mail-code")
    public String getMailCode(@RequestParam(value = "mail") String mail) {
        System.out.println(mail);
        userService.getVerifyCode(mail);
        return "验证码已发送";
    }
    @RequestMapping("/to-sign")
    public String toSign(@RequestBody User user) {
        return  userService.toSign(user);
    }
    @RequestMapping("/to-login")
    public String toLogin(@RequestBody User user) {
        return userService.toLogin(user);
    }
}
