package com.example.service;

import com.example.entity.Comment;
import com.example.entity.SComment;

import java.util.List;

public interface SCommentService {
    String addSComment(SComment sComment);

    List<SComment> getAllSCommentByID(int comment_id);
}
