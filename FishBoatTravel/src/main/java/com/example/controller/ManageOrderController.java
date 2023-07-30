package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Order;
import com.example.service.ManageOrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage-order")
public class ManageOrderController {
    @Resource
    ManageOrderService manageOrderService;

    @RequestMapping("/get-all-order")
    public IPage<Order> getAllOrder(@RequestParam(value = "current_page") int current_page,
                                    @RequestParam(value = "total_page") int total_page) {

        return manageOrderService.getAllOrder(current_page, total_page);
    }

    @RequestMapping("/search-by-user")
    public IPage<Order> searchByUser(@RequestParam(value = "user_name") String user_name,
                                     @RequestParam(value = "current_page") int current_page,
                                     @RequestParam(value = "total_page") int total_page) {
        return manageOrderService.searchByUser(user_name, current_page, total_page);
    }

    @RequestMapping("/update-order")
    public String updateOrder(@RequestBody Order order) {
        return manageOrderService.updateOrder(order);
    }

    @RequestMapping("/add-order")
    public int addOrder(@RequestBody Order order) {
        System.out.println(order.getOrder_date());
        return manageOrderService.addOrder(order);
    }

    @RequestMapping("/get-order")
    public List<Order> getOrderById(@RequestParam(value = "order_id") int order_id) {
        return manageOrderService.getOrderById(order_id);
    }

    @RequestMapping("/get-order-user")
    public List<Order> getOrderByUserName(@RequestParam(value = "user_name") String user_name) {
        return manageOrderService.getOrderByUserName(user_name);
    }

    @RequestMapping("/pay-update")
    public String payUpdate(@RequestParam(value = "order_id") int order_id) {
        return manageOrderService.payUpdate(order_id);
    }

    @RequestMapping("/pay-cancel")
    public String payCancel(@RequestParam(value = "order_id") int order_id) {
        return manageOrderService.payCancel(order_id);
    }
}
