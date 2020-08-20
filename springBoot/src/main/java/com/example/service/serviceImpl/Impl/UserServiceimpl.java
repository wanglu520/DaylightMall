package com.example.service.serviceImpl.Impl;

import java.util.List;

import com.example.service.dao.User;
import com.example.service.mapper.UserMapper;
import com.example.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceimpl implements UserService {

    @Autowired(required=false)
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
    public User getUserById(String id){
        return userMapper.getUserById(id);
    }

}
