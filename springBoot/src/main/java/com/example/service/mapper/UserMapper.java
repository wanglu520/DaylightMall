package com.example.service.mapper;

import java.util.List;
import java.util.Map;

import com.example.service.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper //指定这是一个操作数据库的mapper
public interface UserMapper {
    List<User> findAll();
    @Select("select user_password, user_account from t_user where user_id=#{id}")
    User getUserById(String id);
    @Select("select user_password, user_account from t_user where user_account=#{myName} and user_password=#{password}")
    List<Map<String, Object>> getUserByUserAccount(@Param("myName") String myName, @Param("password") String password);
}