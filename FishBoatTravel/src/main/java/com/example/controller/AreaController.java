package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Area;
import com.example.entity.MapInfo;
import com.example.service.AreaService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage-area")
public class AreaController {
    @Resource
    AreaService areaService;

    @RequestMapping("/add-area")
    public String addArea(@RequestParam(value = "area_name") String area_name,
                          @RequestParam(value = "area_province") String area_province) {
        return areaService.addArea(area_name, area_province);
    }

    @RequestMapping("/get-all-area")
    IPage<Area> getAllArea(@RequestParam(value = "current_page") int current_page,
                           @RequestParam(value = "total_page") int total_page) {
        return areaService.getAllArea(current_page, total_page);
    }

    @RequestMapping("/search")
    IPage<Area> searchByAreaName(@RequestParam(value = "current_page") int current_page,
                                 @RequestParam(value = "total_page") int total_page,
                                 @RequestParam(value = "area_name") String area_name) {
        return areaService.getAreaByAreaName(current_page, total_page, area_name);
    }

    @RequestMapping("/update-area")
    public String updateArea(@RequestBody Area area) {
        return areaService.updateArea(area);
    }

    @RequestMapping("/show-area")
    public List<MapInfo> showArea() {
        return areaService.showArea();
    }

    @RequestMapping("/search-area")
    List<Area> searchAreaOnUnLogin(@RequestParam(value = "area_name") String area_name) {
        return areaService.searchAreaOnUnLogin(area_name);
    }

    @RequestMapping("/get-area-province")
    List<Area> getAreaAndProvince() {
        return areaService.getAreaAndProvince();
    }
}
