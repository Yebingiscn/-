package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Area;
import com.example.entity.MapInfo;

import java.util.List;

public interface AreaService {
    String addArea(String area_name, String area_province);

    String updateArea(Area area);

    IPage<Area> getAllArea(int current_page, int total);

    IPage<Area> getAreaByAreaName(int current_page, int total, String area_name);

    List<MapInfo> showArea();

    long getAreaCount();

    List<Area> searchAreaOnUnLogin(String area_name);

    List<Area> getAreaAndProvince();
}
