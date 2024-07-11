package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Area;
import com.example.entity.MapInfo;
import com.example.mapper.AreaMapping;
import com.example.service.AreaService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.config.ResponseCodeConfig.*;

@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    AreaMapping areaMapping;
    Logger logger;

    @Override
    public String addArea(String area_name, String area_province) {
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("area_name", area_name);
        logger.log(Level.INFO, "" + areaMapping.selectList(queryWrapper).isEmpty());
        if (areaMapping.selectList(queryWrapper).isEmpty()) {
            Area area = new Area();
            area.setArea_name(area_name);
            area.setArea_province(area_province);
            return areaMapping.insert(area) != DATABASE_NOT_FOUND ? ADD_SUCCESS : ADD_FAILURE;
        } else {
            return ADD_SUCCESS;
        }
    }

    @Override
    public String updateArea(Area area) {
        UpdateWrapper<Area> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("area_id", area.getArea_id())
                .set("area_content", area.getArea_content());
        return areaMapping.update(null, updateWrapper) != -1 ? UPDATE_SUCCESS : UPDATE_FAILURE;
    }

    @Override
    public IPage<Area> getAllArea(int current_page, int total) {
        IPage<Area> page = new Page<>(current_page, 6, total);
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        areaMapping.selectList(null);
        return areaMapping.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Area> getAreaByAreaName(int current_page, int total, String area_name) {
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("area_name", area_name); //ne不等于
        IPage<Area> page = new Page<>(current_page, 6, total);
        areaMapping.selectList(queryWrapper);
        return areaMapping.selectPage(page, queryWrapper);
    }

    @Override
    public List<MapInfo> showArea() {
        System.out.println(areaMapping.getAreaNum());
        return areaMapping.getAreaNum();
    }

    @Override
    public long getAreaCount() {
        return areaMapping.selectCount(null);
    }

    @Override
    public List<Area> searchAreaOnUnLogin(String area_name) {
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("area_name", area_name); //ne不等于
        return areaMapping.selectList(queryWrapper);
    }

    @Override
    public List<Area> getAreaAndProvince() {
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        return areaMapping.selectList(queryWrapper);
    }
}
