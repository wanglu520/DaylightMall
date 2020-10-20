package com.example.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {
    /**
     * 商品查询
     * @param map
     * @return
     */
    List<Map<String, Object>> queryGoods(Map map);
    /**
     * 商品查询总条数
     * @param map
     * @return
     */
    Integer queryGoodsTotal(Map map);
}
