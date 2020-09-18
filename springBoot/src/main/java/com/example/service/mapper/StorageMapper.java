package com.example.service.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageMapper {
    /**
     * 上传图片
     * @param map
     * @return
     */
    Integer uploadPhoto(Map map);
}
