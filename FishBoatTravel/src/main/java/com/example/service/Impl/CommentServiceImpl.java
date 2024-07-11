package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Comment;
import com.example.mapper.CommentMapper;
import com.example.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static com.example.config.ResponseCodeConfig.*;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;

    @Override
    public String addComment(Comment comment) {
        Date date = new Date(System.currentTimeMillis());//获取当前时间
        comment.setRate_time(date);
        return commentMapper.insert(comment) != DATABASE_NOT_FOUND ? ADD_SUCCESS : ADD_FAILURE;
    }

    @Override
    public List<Comment> getAllComment(String road_name) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("road_name", road_name); //ne不等于
        return commentMapper.selectList(queryWrapper);
    }
}
