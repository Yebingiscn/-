package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminData {
    long count_user;
    long count_order;
    long count_ship;
    long count_owner;
    long count_road;
    long count_area;
}
