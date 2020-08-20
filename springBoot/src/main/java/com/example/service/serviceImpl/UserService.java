package com.example.service.serviceImpl;

import java.util.List;

import com.example.service.dao.User;

public interface UserService {
    List<User> findAll();
    User getUserById(String id);
}
