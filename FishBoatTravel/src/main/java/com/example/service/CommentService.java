package com.example.service;

import com.example.entity.Comment;

import java.util.List;

public interface CommentService {
    String addComment(Comment comment);

    List<Comment> getAllComment(String road_name);
}
