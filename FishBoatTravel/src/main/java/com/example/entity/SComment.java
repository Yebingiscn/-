package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
@TableName("scomment")
public class SComment {
    @TableId(type = IdType.AUTO)
    int SComment_id;
    int comment_id;
    String user_name;
    String SComment_content;
    Date comment_time;
}
