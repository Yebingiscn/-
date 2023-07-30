package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Ship;

import java.util.List;

public interface ShipService {
    IPage<Ship> getAllShip(int current_page, int total);

    IPage<Ship> searchByOwner(String ship_owner, int current_page, int total);

    String updateShip(Ship ship);

    String delShip(int ship_id);

    String addShip(Ship ship);

    long getShipCount();

    List<Ship> getShip(String road_name);
}
