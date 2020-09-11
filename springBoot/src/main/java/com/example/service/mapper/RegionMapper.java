package com.example.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionMapper {
    //区域信息查询
    List<Map<String, Object>> queryRegion();
}
