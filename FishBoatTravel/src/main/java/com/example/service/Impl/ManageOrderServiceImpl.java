package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Order;
import com.example.mapper.OrderMapping;
import com.example.service.ManageOrderService;
import com.example.service.ManageUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ManageOrderServiceImpl implements ManageOrderService {
    @Resource
    OrderMapping orderMapping;
    @Resource
    ManageUserService manageUserService;
    @Resource
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String from;

    @Override
    public IPage<Order> getAllOrder(int current_page, int total) {
        IPage<Order> page = new Page<>(current_page, 6, total);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        orderMapping.selectList(null);
        return orderMapping.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Order> searchByUser(String user_name, int current_page, int total) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.like("user_name", user_name); //ne不等于
        IPage<Order> page = new Page<>(current_page, 6, total);
        orderMapping.selectList(queryWrapper);
        return orderMapping.selectPage(page, queryWrapper);
    }

    @Override
    public String updateOrder(Order order) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", order.getOrder_id())
                .set("user_name", order.getUser_name()).set("road_name", order.getRoad_name())
                .set("order_date", order.getOrder_date()).set("sum_price", order.getSum_price())
                .set("friend_info", order.getFriend_info()).set("order_state", order.getOrder_state())
                .set("ship", order.getShip());
        int update = orderMapping.update(null, updateWrapper);
        if (update != -1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @Override
    public long getOrderCount() {
        return orderMapping.selectCount(null);
    }

    @Override
    public int addOrder(Order order) {
        int insert = orderMapping.insert(order);
        if (insert != -1) {
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.select();
            queryWrapper.eq("order_date", order.getOrder_date());
            List<Order> orders = orderMapping.selectList(queryWrapper);
            return orders.get(0).getOrder_id();
        } else return -1;
    }

    @Override
    public List<Order> getOrderById(int order_id) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("order_id", order_id);
        return orderMapping.selectList(queryWrapper);
    }

    @Override
    public List<Order> getOrderByUserName(String user_name) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        queryWrapper.eq("user_name", user_name);
        return orderMapping.selectList(queryWrapper);
    }

    @Override
    public String payUpdate(int order_id) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", order_id).set("order_state", "已支付");
        int update = orderMapping.update(null, updateWrapper);
        if (update != -1) {
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.select();
            queryWrapper.eq("order_id", order_id);
            List<Order> orders = orderMapping.selectList(queryWrapper);
            Order order = orders.get(0);
            System.out.println(order);
            String mail = manageUserService.getMail(order.getUser_name());
            System.out.println(mail);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("[渔之旅]新订单通知");
            simpleMailMessage.setText("感谢你选择渔之旅\n你的路线：" + order.getRoad_name() + " 已订购，预计出发时间为"
                    + simpleDateFormat.format(order.getOrder_date()) +
                    "\n如果你有任何疑问，欢迎联系渔之旅。联系电话：400-8820-8830，具体上船时间及安排会有工作人员电话与你联系");
            simpleMailMessage.setTo(mail);
            simpleMailMessage.setFrom(from);
            mailSender.send(simpleMailMessage);
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @Override
    public String payCancel(int order_id) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", order_id).set("order_state", "已取消");
        int update = orderMapping.update(null, updateWrapper);
        if (update != -1) {
            return "取消成功";
        } else {
            return "取消失败";
        }
    }
}
