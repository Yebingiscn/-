package com.example.service.Impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    StringRedisTemplate template;
    @Resource
    JavaMailSender mailSender;
    @Resource
    UserMapper userMapper;
    @Value("${spring.mail.username}")
    String from;

    @Override
    public void getVerifyCode(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("[渔之旅]注册验证码");
        Random random = new Random();
        int code = random.nextInt(8999) + 1000;
        System.out.println(code);
        template.opsForValue().set("verify:code" + email, code + "", 3, TimeUnit.MINUTES);
        simpleMailMessage.setText("欢迎加入渔之旅\n注册验证码为：" + code + "，三分钟内有效，非本人操作请忽略");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(from);
        mailSender.send(simpleMailMessage);
    }

    @Override
    public String toSign(User user) {
        if (doVerify(user.getMail(), user.getMail_code())) {
            int insert = userMapper.insert(user);
            if (insert == -1) {
                return "插入失败";
            } else {
                return "注册成功";
            }
        }
        return "邮件验证码错误";
    }

    @Override
    public String toLogin(User user) {
        System.out.println(user);
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", user.getUser_name());
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
        if (users.isEmpty()) {
            return "账户不存在，请先登录";
        }
        User user1 = users.get(0);
        if (user1.getIs_admin() == -1) {
            return "你已被封禁，请联系管理员";
        }
        if (user1.getPassword().equals(user.getPassword())) {
            return "登录成功";
        } else {
            return "密码错误";
        }
    }

    public boolean doVerify(String mail, String verify) {
        String isExitsInRedis = template.opsForValue().get("verify:code" + mail);
        System.out.println(isExitsInRedis + " " + verify);
        if (isExitsInRedis == null) {
            return false;
        }
        if (!isExitsInRedis.equals(verify)) {
            return false;
        }
        template.delete("verify:code" + mail);
        return true;
    }
}
