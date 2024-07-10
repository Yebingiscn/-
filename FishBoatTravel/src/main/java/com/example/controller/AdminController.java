package com.example.controller;

import com.example.entity.AdminData;
import com.example.entity.User;
import com.example.service.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;
    @Resource
    AreaService areaService;
    @Resource
    ManageBoatOwnerService manageBoatOwnerService;
    @Resource
    ManageOrderService manageOrderService;
    @Resource
    ManageUserService manageUserService;
    @Resource
    RoadService roadService;
    @Resource
    ShipService shipService;

    @RequestMapping("/to-login")
    public String toLoginAdmin(@RequestBody User user) {
        System.out.println(user.getPassword());
        return adminService.toLoginAdmin(user);
    }

    @RequestMapping("/show-admin")
    public List<User> showAdmin() {
        return adminService.showAdmin();
    }

    @RequestMapping("/show-user-pub")
    public List<User> showUserPublic() {
        return adminService.showUserPublic();
    }

    @RequestMapping("/add-admin")
    public String addAdmin(@RequestBody User user) {
        return adminService.addAdmin(user);
    }

    @RequestMapping("/del-admin")
    public String delAdmin(@RequestBody User user) {
        return adminService.delAdmin(user);
    }

    @RequestMapping("/get-data")
    public AdminData getData() {
        return new AdminData(manageUserService.getUserCount(), manageOrderService.getOrderCount(),
                shipService.getShipCount(), manageBoatOwnerService.getBoatOwnerCount(),
                roadService.getRoadCount(), areaService.getAreaCount());
    }
}
