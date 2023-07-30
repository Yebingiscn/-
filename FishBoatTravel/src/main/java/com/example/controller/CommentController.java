package com.example.controller;

import com.example.entity.Comment;
import com.example.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;
    @RequestMapping("/add-comment")
    public String addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @RequestMapping("/get-comment")
    public List<Comment> getAllComment(@RequestParam("road_name") String road_name) {
        return commentService.getAllComment(road_name);
    }
}
