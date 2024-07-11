package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.UserPub;
import com.example.mapper.UserPubMapper;
import com.example.service.ManageUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import io.micrometer.common.util.StringUtils;

import java.util.List;

@Service
public class ManageUserServiceImpl implements ManageUserService {
    @Resource
    UserPubMapper userPubMapper;

    @Override
    public IPage<UserPub> getAllUser(int current_page, int total) {
        IPage<UserPub> page = new Page<>(current_page, 6, total);
        QueryWrapper<UserPub> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        userPubMapper.selectList(null);
        return userPubMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<UserPub> selectByUserName(String user_name, int current_page, int total) {
        QueryWrapper<UserPub> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("user_name", user_name); //ne不等于
        IPage<UserPub> page = new Page<>(current_page, 6, total);
        userPubMapper.selectList(queryWrapper);
        return userPubMapper.selectPage(page, queryWrapper);
    }

    @Override
    public String blockUser(String user_name) {
        UpdateWrapper<UserPub> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", user_name).set("is_admin", -1);
        return userPubMapper.update(null, updateWrapper) != -1 ? "操作成功" : "操作失败";
    }

    @Override
    public String unBlockUser(String user_name) {
        UpdateWrapper<UserPub> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", user_name).set("is_admin", 0);
        return userPubMapper.update(null, updateWrapper) != -1 ? "操作成功" : "操作失败";
    }

    @Override
    public long getUserCount() {
        return userPubMapper.selectCount(null);
    }

    @Override
    public String isAddInfo(String user_name) {
        QueryWrapper<UserPub> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("user_name", user_name); //ne不等于
        List<UserPub> userPubs = userPubMapper.selectList(queryWrapper);
        UserPub userPub = userPubs.get(0);
        System.out.println(userPub);
        if (!StringUtils.isBlank(userPub.getTelephone()) ||
                !StringUtils.isBlank(userPub.getMail()) ||
                userPub.getAge() != 0) {
            return "信息已添加";
        } else {
            return "信息未添加";
        }
    }

    @Override
    public String addInfo(UserPub userPub) {
        UpdateWrapper<UserPub> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", userPub.getUser_name())
                .set("telephone", userPub.getTelephone()).set("age", userPub.getAge());
        return userPubMapper.update(null, updateWrapper) != -1 ? "添加成功" : "添加失败";
    }

    @Override
    public String getMail(String user_name) {
        QueryWrapper<UserPub> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("user_name", user_name); //ne不等于
        List<UserPub> userPubs = userPubMapper.selectList(queryWrapper);
        UserPub userPub = userPubs.get(0);
        return userPub.getMail();
    }

    @Override
    public String changePwd(UserPub userPub) {
        UpdateWrapper<UserPub> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userPub.getUser_id())
                .set("password", userPub.getPassword());
        return userPubMapper.update(null, updateWrapper) != -1 ? "更新成功" : "更新失败";
    }

    @Override
    public String updateInfo(UserPub userPub) {
        UpdateWrapper<UserPub> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userPub.getUser_id())
                .set("sex", userPub.getSex())
                .set("telephone", userPub.getTelephone()).set("age", userPub.getAge())
                .set("mail", userPub.getMail()).set("user_img", userPub.getUser_img());
        return userPubMapper.update(null, updateWrapper) != -1 ? "更新成功" : "更新失败";
    }

    @Override
    public List<UserPub> getUserInfo(String user_name) {
        QueryWrapper<UserPub> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("user_name", user_name); //ne不等于
        return userPubMapper.selectList(queryWrapper);
    }

}
