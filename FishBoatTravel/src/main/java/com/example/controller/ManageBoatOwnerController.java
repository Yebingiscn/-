package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.ShipOwner;
import com.example.service.ManageBoatOwnerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage-owner")
public class ManageBoatOwnerController {
    @Resource
    ManageBoatOwnerService manageBoatOwnerService;

    @RequestMapping("/get-all-owner")
    public IPage<ShipOwner> selectAllOwner(@RequestParam(value = "current_page") int current_page,
                                           @RequestParam(value = "total_page") int total_page) {
        return manageBoatOwnerService.selectAllOwner(current_page, total_page);
    }

    @RequestMapping("/search")
    public IPage<ShipOwner> selectByUserName(@RequestParam(value = "current_page") int current_page,
                                             @RequestParam(value = "total_page") int total_page,
                                             @RequestParam(value = "ship_owner") String ship_owner) {
        return manageBoatOwnerService.selectByUserName(current_page, total_page, ship_owner);
    }

    @RequestMapping("/add-owner")
    public String addOwner(@RequestBody ShipOwner shipOwner) {
        return manageBoatOwnerService.addOwner(shipOwner);
    }

    @RequestMapping("/del-owner")
    public String delOwner(@RequestParam(value = "owner_id") int owner_id) {
        return manageBoatOwnerService.delOwner(owner_id);
    }

    @RequestMapping("/update-info")
    public String updateInfo(@RequestBody ShipOwner shipOwner) {
        return manageBoatOwnerService.updateInfo(shipOwner);
    }

    @RequestMapping("/get-owner-name")
    public List<ShipOwner> getAllOwner() {
        return manageBoatOwnerService.getAllOwner();
    }
}
