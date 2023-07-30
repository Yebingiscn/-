package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Road {
    @TableId
    int road_id;
    String road_image;
    String road_name;
    String road_indicate;
    String area_name;
    String type;
    String ship_type;
    double fee;
    String state;
    double location_1x;
    double location_1y;
    double location_2x;
    double location_2y;
    double location_3x;
    double location_3y;
}
