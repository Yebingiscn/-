package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Order;

import java.util.List;

public interface ManageOrderService {
    IPage<Order> getAllOrder(int current_page, int total);

    IPage<Order> searchByUser(String user_name, int current_page, int total);

    String updateOrder(Order order);

    long getOrderCount();

    int addOrder(Order order);

    List<Order> getOrderById(int order_id);

    List<Order> getOrderByUserName(String user_name);

    String payUpdate(int order_id);

    String payCancel(int order_id);
}
