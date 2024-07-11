package com.example.service.Impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import io.micrometer.common.util.StringUtils;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.config.ResponseCodeConfig.*;

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
    Logger logger;

    @Override
    public String getVerifyCode(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("[渔之旅]注册验证码");
        Random random = new Random();
        int code = random.nextInt(8999) + 1000;
        logger.log(Level.INFO, String.valueOf(code));
        System.out.println(code);
        template.opsForValue().set("verify:code" + email, code + "", 3, TimeUnit.MINUTES);
        simpleMailMessage.setText("欢迎加入渔之旅\n注册验证码为：" + code + "，三分钟内有效，非本人操作请忽略");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(from);
        try {
            mailSender.send(simpleMailMessage);
            return VERIFY_CODE_SENT_SUCCESS;
        } catch (Exception exception) {
            logger.log(Level.WARNING, exception.toString());
            return VERIFY_CODE_SENT_FAILURE;
        }
    }

    @Override
    public String toSign(User user) {
        if (doVerify(user.getMail(), user.getMail_code())) {
            return userMapper.insert(user) != DATABASE_NOT_FOUND ? SIGN_SUCCESS : SIGN_FAILURE;
        }
        return VERIFY_CODE_ERROR;
    }

    @Override
    public String toLogin(User user) {
        logger.log(Level.INFO, user.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", user.getUser_name());
        List<User> users = userMapper.selectByMap(map);
        logger.log(Level.INFO, users.toString());
        if (users.isEmpty()) {
            return LOGGING_FAILURE;
        }
        User user1 = users.get(0);
        if (user1.getIs_admin() == -1) {
            return USER_BLOCK;
        }
        return user1.getPassword().equals(user.getPassword()) ? LOGGING_SUCCESS : PASSWORD_ERROR;
    }

    public boolean doVerify(String mail, String verify) {
        String isExitsInRedis = template.opsForValue().get("verify:code" + mail);
        logger.log(Level.INFO, isExitsInRedis + " " + verify);
        if (!StringUtils.isBlank(isExitsInRedis) && isExitsInRedis.equals(verify)) {
            template.delete("verify:code" + mail);
            return true;
        }
        return false;
    }
}
