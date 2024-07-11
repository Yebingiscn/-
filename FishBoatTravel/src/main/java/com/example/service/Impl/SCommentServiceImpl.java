package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.SComment;
import com.example.mapper.SCommentMapper;
import com.example.service.SCommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static com.example.config.ResponseCodeConfig.*;

@Service
public class SCommentServiceImpl implements SCommentService {
    @Resource
    SCommentMapper sCommentMapper;

    @Override
    public String addSComment(SComment sComment) {
        Date date = new Date(System.currentTimeMillis());//获取当前时间
        sComment.setComment_time(date);
        return sCommentMapper.insert(sComment) != DATABASE_NOT_FOUND ? ADD_SUCCESS : ADD_FAILURE;
    }

    @Override
    public List<SComment> getAllSCommentByID(int comment_id) {
        QueryWrapper<SComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("comment_id", comment_id); //ne不等于
        return sCommentMapper.selectList(queryWrapper);
    }
}
