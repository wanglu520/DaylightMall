package com.example.service.mapper;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    /**
     * 所属类型查询
     * @param map
     * @return
     */
    List<Map<String,Object>> queryCategory(Map map);
}
