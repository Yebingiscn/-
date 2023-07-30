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

@Service
public class ManageBoatOwnerServiceImpl implements ManageBoatOwnerService {
    @Resource
    ShipOwnerMapper shipOwnerMapper;

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
        int insert = shipOwnerMapper.insert(shipOwner);
        if (insert != -1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }


    @Override
    public String delOwner(int owner_id) {
        int delete = shipOwnerMapper.deleteById(owner_id);
        if (delete != -1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public String updateInfo(ShipOwner shipOwner) {
        UpdateWrapper<ShipOwner> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("sowner_id", shipOwner.getOwner_id())
                .set("ship_owner", shipOwner.getShip_owner()).set("telephone", shipOwner.getTelephone());
        int update = shipOwnerMapper.update(null, updateWrapper);
        if (update != -1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
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
