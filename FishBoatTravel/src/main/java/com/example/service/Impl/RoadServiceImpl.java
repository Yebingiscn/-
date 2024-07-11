package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Road;
import com.example.mapper.RoadMapping;
import com.example.service.RoadService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.config.ResponseCodeConfig.*;

@Service
public class RoadServiceImpl implements RoadService {
    @Resource
    RoadMapping roadMapping;

    @Override
    public IPage<Road> getAllRoad(int current_page, int total) {
        IPage<Road> page = new Page<>(current_page, 6, total);
        QueryWrapper<Road> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        roadMapping.selectList(null);
        return roadMapping.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Road> searchByRoad(String road_search, int current_page, int total) {
        QueryWrapper<Road> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("road_name", road_search); //ne不等于
        IPage<Road> page = new Page<>(current_page, 6, total);
        roadMapping.selectList(queryWrapper);
        return roadMapping.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Road> searchByArea(String road_search, int current_page, int total) {
        QueryWrapper<Road> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("area_name", road_search); //ne不等于
        IPage<Road> page = new Page<>(current_page, 6, total);
        roadMapping.selectList(queryWrapper);
        return roadMapping.selectPage(page, queryWrapper);
    }

    @Override
    public List<Road> searchRoadOnUnLogin(String road_search) {
        QueryWrapper<Road> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("road_name", road_search); //ne不等于
        return roadMapping.selectList(queryWrapper);
    }

    @Override
    public String updateRoad(Road road) {
        UpdateWrapper<Road> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("road_id", road.getRoad_id())
                .set("road_name", road.getRoad_name()).set("road_indicate", road.getRoad_indicate())
                .set("area_name", road.getArea_name()).set("type", road.getType())
                .set("ship_type", road.getShip_type()).set("fee", road.getFee())
                .set("road_image", road.getRoad_image())
                .set("state", road.getState()).set("location_1x", road.getLocation_1x())
                .set("location_1y", road.getLocation_1y()).set("location_2x", road.getLocation_2x())
                .set("location_2y", road.getLocation_2y()).set("location_3x", road.getLocation_3x())
                .set("location_3y", road.getLocation_3y());
        return roadMapping.update(null, updateWrapper) != DATABASE_NOT_FOUND ? UPDATE_SUCCESS : UPDATE_FAILURE;
    }

    @Override
    public String delRoad(int road_id) {
        return roadMapping.deleteById(road_id) != DATABASE_NOT_FOUND ? DELETE_SUCCESS : DELETE_FAILURE;
    }

    @Override
    public String addRoad(Road road) {
        return roadMapping.insert(road) != DATABASE_NOT_FOUND ? ADD_SUCCESS : ADD_FAILURE;
    }

    @Override
    public List<Road> getRoadName() {
        QueryWrapper<Road> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("state", "正常"); //ne不等于
        return roadMapping.selectList(queryWrapper);
    }

    @Override
    public long getRoadCount() {
        return roadMapping.selectCount(null);
    }
}
