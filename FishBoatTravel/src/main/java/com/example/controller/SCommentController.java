package com.example.controller;

import com.example.entity.SComment;
import com.example.service.SCommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sec-comment")
public class SCommentController {
    @Resource
    SCommentService sCommentService;

    @RequestMapping("/get-comment")
    List<SComment> getSComment(@RequestParam(value = "comment_id") int comment_id) {
        List<SComment> allSCommentByID = sCommentService.getAllSCommentByID(comment_id);
        System.out.println(allSCommentByID);
        return sCommentService.getAllSCommentByID(comment_id);
    }

    @RequestMapping("/add-comment")
    String addComment(@RequestBody SComment SComment) {
        return sCommentService.addSComment(SComment);
    }
}
