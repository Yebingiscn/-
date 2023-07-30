package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Road;
import com.example.service.RoadService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage-road")
public class RoadController {
    @Resource
    RoadService roadService;

    @RequestMapping("/get-all-road")
    public IPage<Road> getAllRoad(@RequestParam(value = "current_page") int current_page,
                                  @RequestParam(value = "total_page") int total_page) {
        return roadService.getAllRoad(current_page, total_page);
    }

    @RequestMapping("/search")
    public IPage<Road> selectByUserName(@RequestParam(value = "current_page") int current_page,
                                        @RequestParam(value = "total_page") int total_page,
                                        @RequestParam(value = "road_search") String road_search) {
        return roadService.searchByRoad(road_search, current_page, total_page);
    }

    @RequestMapping("/search-road")
    public List<Road> searchRoadOnUnLogin(@RequestParam(value = "road_search") String road_search) {
        return roadService.searchRoadOnUnLogin(road_search);
    }

    @RequestMapping("/get-road-area")
    public IPage<Road> selectByAreaName(@RequestParam(value = "current_page") int current_page,
                                        @RequestParam(value = "total_page") int total_page,
                                        @RequestParam(value = "road_search") String road_search) {
        return roadService.searchByArea(road_search, current_page, total_page);
    }

    @RequestMapping("/add-road")
    public String addRoad(@RequestBody Road road) {
        return roadService.addRoad(road);
    }

    @RequestMapping("/del-road")
    public String delRoad(@RequestParam(value = "road_id") int road_id) {
        return roadService.delRoad(road_id);
    }

    @RequestMapping("/update-road")
    public String updateRoad(@RequestBody Road road) {
        return roadService.updateRoad(road);
    }

    @RequestMapping("/get-road-name")
    public List<Road> getRoadName() {
        return roadService.getRoadName();
    }
}
