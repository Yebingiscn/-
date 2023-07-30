package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Ship;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipMapping extends BaseMapper<Ship> {
}
