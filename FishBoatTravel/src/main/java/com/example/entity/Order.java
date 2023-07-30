package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("user_order")
public class Order {
    @TableId(type = IdType.AUTO)
    int order_id;
    String user_name;
    String road_name;
    Date order_date;
    double sum_price;
    String friend_info;
    String order_state;
    String ship;
}
