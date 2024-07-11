package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.ShipOwner;
import com.example.mapper.ShipOwnerMapper;
import com.example.service.ManageBoatOwnerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.config.ResponseCodeConfig.*;

@Service
public class ManageBoatOwnerServiceImpl implements ManageBoatOwnerService {
    @Resource
    ShipOwnerMapper shipOwnerMapper;
    Logger logger;

    @Override
    public IPage<ShipOwner> selectAllOwner(int current_page, int total) {
        IPage<ShipOwner> page = new Page<>(current_page, 6, total);
        QueryWrapper<ShipOwner> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        shipOwnerMapper.selectList(null);
        return shipOwnerMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<ShipOwner> selectByUserName(int current_page, int total, String ship_owner) {
        QueryWrapper<ShipOwner> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("ship_owner", ship_owner); //ne不等于
        IPage<ShipOwner> page = new Page<>(current_page, 6, total);
        shipOwnerMapper.selectList(queryWrapper);
        return shipOwnerMapper.selectPage(page, queryWrapper);
    }

    @Override
    public String addOwner(ShipOwner shipOwner) {
        return shipOwnerMapper.insert(shipOwner) != DATABASE_NOT_FOUND ? ADD_SUCCESS : ADD_FAILURE;
    }


    @Override
    public String delOwner(int owner_id) {
        try {
            return shipOwnerMapper.deleteById(owner_id) != DATABASE_NOT_FOUND ? DELETE_SUCCESS : DELETE_FAILURE;
        } catch (Exception exception) {
            logger.log(Level.WARNING, exception.toString());
            return DELETE_FAILURE;
        }
    }

    @Override
    public String updateInfo(ShipOwner shipOwner) {
        UpdateWrapper<ShipOwner> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("sowner_id", shipOwner.getOwner_id())
                .set("ship_owner", shipOwner.getShip_owner()).set("telephone", shipOwner.getTelephone());
        return shipOwnerMapper.update(null, updateWrapper) != DATABASE_NOT_FOUND ? UPDATE_SUCCESS : UPDATE_FAILURE;
    }

    @Override
    public List<ShipOwner> getAllOwner() {
        QueryWrapper<ShipOwner> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        return shipOwnerMapper.selectList(queryWrapper);
    }

    @Override
    public long getBoatOwnerCount() {
        return shipOwnerMapper.selectCount(null);
    }
}
