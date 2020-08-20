package com.example.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper //指定这是一个操作数据库的mapper
public interface AutoMeunMapper {
    @Select("select * from t_auto_menu where user_id=#{id}")
    public List<Map<String, Object>> queryAutoMenu(@Param("id") String id);
    @Select("select * from t_mian_menu where id in (${mainId})")
    public List<Map<String, Object>> queryMainMenu(@Param("mainId") String mainId);
    @Select("select * from t_child_menu where id in (${childId})")
    public List<Map<String, Object>> queryChildMenu(@Param("childId") String childId);
}
