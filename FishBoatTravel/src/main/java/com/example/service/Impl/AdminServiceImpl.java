package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.config.ResponseCodeConfig.*;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    UserMapper userMapper;
    Logger logger;

    @Override
    public String toLoginAdmin(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", user.getUser_name());
        List<User> users = userMapper.selectByMap(map);
        if (users.isEmpty()) {
            return LOGGING_FAILURE;
        }
        User user1 = users.get(0);
        logger.log(Level.INFO, user.toString());
        if (user1.getIs_admin() != 1) {
            return NON_ADMINISTRATORS;
        }
        logger.log(Level.INFO, "用户密码" + user1.getPassword());
        logger.log(Level.INFO, "保存的密码" + user1.getPassword());
        return user1.getPassword().equals(user.getPassword()) ? LOGGING_SUCCESS : PASSWORD_ERROR;
    }

    @Override
    public List<User> showAdmin() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id", "user_name", "mail", "telephone")
                .eq("is_admin", 1);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public List<User> showUserPublic() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("is_admin", 0); //ne不等于
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public String addAdmin(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", user.getUser_name()).set("is_admin", 1);
        return userMapper.update(null, updateWrapper) != DATABASE_NOT_FOUND ? ADD_SUCCESS : ADD_FAILURE;
    }

    @Override
    public String delAdmin(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", user.getUser_name()).set("is_admin", 0);
        return userMapper.update(null, updateWrapper) != DATABASE_NOT_FOUND ? DELETE_SUCCESS : DELETE_FAILURE;
    }
}
