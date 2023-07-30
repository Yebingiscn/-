package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ship {
    @TableId(type = IdType.AUTO)
    int ship_id;
    String ship_name;
    @TableField("ship_loginid")
    String ship_login_id;
    String ship_owner;
    String ship_img;
    String road_name;
    String area_name;
    String ship_type;
    String ship_state;
}
