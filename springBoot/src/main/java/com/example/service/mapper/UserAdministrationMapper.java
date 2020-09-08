package com.example.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAdministrationMapper {
    //会员管理查询
    List<Map<String,Object>> queryUserDetail(Map map);
    //会员管理总条数
    Integer queryUserDetailTotal(Map map);
}
