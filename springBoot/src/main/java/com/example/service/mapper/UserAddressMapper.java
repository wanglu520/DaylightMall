package com.example.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper {

    //会员收货地址查询
    List<Map<String, Object>> queryUserAddress(Map map);
    //收货地址总条数
    Integer queryUserAddressTotal(Map map);
}
