package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("ship_owner")
public class ShipOwner {
    @TableId("sowner_id")
    int owner_id;
    String ship_owner;
    String telephone;
}
