package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.ShipOwner;

import java.util.List;

public interface ManageBoatOwnerService {
    IPage<ShipOwner> selectAllOwner(int current_page, int total);

    IPage<ShipOwner> selectByUserName(int current_page, int total, String ship_owner);

    String addOwner(ShipOwner shipOwner);

    String delOwner(int owner_id);

    String updateInfo(ShipOwner shipOwner);

    List<ShipOwner> getAllOwner();

    long getBoatOwnerCount();
}
