package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Ship;
import com.example.mapper.ShipMapping;
import com.example.service.ShipService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {
    @Resource
    ShipMapping shipMapping;

    @Override
    public IPage<Ship> getAllShip(int current_page, int total) {
        IPage<Ship> page = new Page<>(current_page, 6, total);
        QueryWrapper<Ship> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        shipMapping.selectList(null);
        return shipMapping.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Ship> searchByOwner(String ship_owner, int current_page, int total) {
        QueryWrapper<Ship> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("ship_owner", ship_owner); //ne不等于
        IPage<Ship> page = new Page<>(current_page, 6, total);
        shipMapping.selectList(queryWrapper);
        return shipMapping.selectPage(page, queryWrapper);
    }

    @Override
    public String updateShip(Ship ship) {
        UpdateWrapper<Ship> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("ship_id", ship.getShip_id())
                .set("ship_name", ship.getShip_name()).set("road_name", ship.getRoad_name())
                .set("area_name", ship.getArea_name()).set("ship_type", ship.getShip_type())
                .set("ship_owner", ship.getShip_owner()).set("ship_state", ship.getShip_state())
                .set("ship_img", ship.getShip_img()).set("ship_loginid", ship.getShip_login_id());
        return shipMapping.update(null, updateWrapper) != -1 ? "更新成功" : "更新失败";
    }

    @Override
    public String delShip(int ship_id) {
        return shipMapping.deleteById(ship_id) != -1 ? "删除成功" : "删除失败";
    }

    @Override
    public String addShip(Ship ship) {
        return shipMapping.insert(ship) != -1 ? "添加成功" : "添加失败";

    }

    @Override
    public long getShipCount() {
        return shipMapping.selectCount(null);
    }

    @Override
    public List<Ship> getShip(String road_name) {
        QueryWrapper<Ship> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("road_name", road_name); //ne不等于
        return shipMapping.selectList(queryWrapper);
    }
}
