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

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    UserMapper userMapper;

    @Override
    public String toLoginAdmin(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", user.getUser_name());
        List<User> users = userMapper.selectByMap(map);
        if (users.isEmpty()) {
            return "账户不存在，请先登录";
        }
        User user1 = users.get(0);
        System.out.println(users);
        if (user1.getIs_admin() != 1) {
            return "你不是管理员";
        }
        if (!user1.getPassword().equals(user.getPassword())) {
            System.out.println(user1.getPassword());
            System.out.println(user.getPassword());
            return "密码错误";
        } else {
            return "登录成功";
        }
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
        int update = userMapper.update(null, updateWrapper);
        if (update != -1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @Override
    public String delAdmin(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", user.getUser_name()).set("is_admin", 0);
        int update = userMapper.update(null, updateWrapper);
        if (update != -1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
