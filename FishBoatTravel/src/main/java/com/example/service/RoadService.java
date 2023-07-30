package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Road;

import java.util.List;

public interface RoadService {
    IPage<Road> getAllRoad(int current_page, int total);

    IPage<Road> searchByRoad(String road_search, int current_page, int total);

    IPage<Road> searchByArea(String road_search, int current_page, int total);

    List<Road> searchRoadOnUnLogin(String road_search);

    String updateRoad(Road road);

    String delRoad(int road_id);

    String addRoad(Road road);

    List<Road> getRoadName();

    long getRoadCount();
}
