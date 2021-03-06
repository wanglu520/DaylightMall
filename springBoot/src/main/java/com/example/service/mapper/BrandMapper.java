package com.example.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandMapper {
    /**
     * 制造商信息查询
     * @param map
     * @return
     */
    List<Map<String, Object>> queryBrand(Map map);
    /**
     * 制造商信息查询
     * @param map
     * @return
     */
    List<Map<String, Object>> queryBrandAll(Map map);

    /**
     * 制造商信息总条数
     * @param map
     * @return
     */
    Integer queryBrandTotal(Map map);

    /**
     *  删除制造商信息
     * @param map
     * @return
     */
    Integer deleteBrand (Map map);

    /**
     * 更新数据
     * @param map
     * @return
     */
    Integer updateBrand(Map map);

    /**
     * 新增
     * @param map
     * @return
     */
    Integer addBrand(Map map);
}
