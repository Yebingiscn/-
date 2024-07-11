package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")

public class UserController {
    @Resource
    UserService userService;
    Logger logger;

    @RequestMapping("/get-mail-code")
    public String getMailCode(@RequestParam(value = "mail") String mail) {
        logger.log(Level.INFO, mail);
        userService.getVerifyCode(mail);
        return userService.getVerifyCode(mail);
    }

    @RequestMapping("/to-sign")
    public String toSign(@RequestBody User user) {
        return userService.toSign(user);
    }

    @RequestMapping("/to-login")
    public String toLogin(@RequestBody User user) {
        return userService.toLogin(user);
    }
}
