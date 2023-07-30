package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Comment {
    @TableId(type = IdType.AUTO)
    int comment_id;
    String comment_content;
    String user_name;
    String road_name;
    Date rate_time;
    int rate_score;
}
