package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapping extends BaseMapper<Order> {
}
