package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Ship;
import com.example.service.ShipService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage-ship")
public class ShipController {
    @Resource
    ShipService shipService;

    @RequestMapping("/get-all-ship")
    IPage<Ship> getAllShip(@RequestParam(value = "current_page") int current_page,
                           @RequestParam(value = "total_page") int total_page) {
        return shipService.getAllShip(current_page, total_page);
    }

    @RequestMapping("search")
    IPage<Ship> searchByOwner(@RequestParam(value = "current_page") int current_page,
                              @RequestParam(value = "total_page") int total_page,
                              @RequestParam(value = "ship_owner") String ship_owner) {
        return shipService.searchByOwner(ship_owner, current_page, total_page);
    }

    @RequestMapping("/add-ship")
    public String addShip(@RequestBody Ship ship) {
        return shipService.addShip(ship);
    }

    @RequestMapping("/del-ship")
    public String delShip(@RequestParam(value = "ship_id") int ship_id) {
        return shipService.delShip( ship_id);
    }

    @RequestMapping("/update-ship")
    public String updateShip(@RequestBody Ship ship) {
        return shipService.updateShip(ship);
    }

    @RequestMapping("/get-ship")
    public List<Ship> getShip(@RequestParam(value = "road_name") String road_name) {
        return shipService.getShip(road_name);
    }
}
