package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Area;
import com.example.entity.MapInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AreaMapping extends BaseMapper<Area> {
    @Select("SELECT SUBSTR(area_province, 1, 2) AS name,COUNT(DISTINCT area_province) AS value " +
            "FROM fishboattravel.area GROUP BY area_province")
    List<MapInfo> getAreaNum();
}
