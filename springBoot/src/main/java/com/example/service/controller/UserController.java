package com.example.service.controller;

import java.util.List;
import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.Utils.JwtUtils;
import com.example.service.dao.User;
import com.example.service.mapper.UserMapper;
import com.example.service.serviceImpl.Impl.BaseServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.example.service.serviceImpl.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseServiceImpl {

    @Autowired(required=false)
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping(value = "/findAll")
    public List<User> findAll(){
        //mybatis sql xml映射
        List<User> users = userMapper.findAll();
        return users;
    }
    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    public User getUserById(@RequestBody Map body){
        String id = (String) body.get("username");
        String password = (String) body.get("password");
        User users = userMapper.getUserById(id);
        return users;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public OutPutObject login(@RequestBody Map body){
        OutPutObject outPutObject = getOutPutObject();
        String username = (String) body.get("username");
        String password = (String) body.get("password");
        //mybatis sql注解
        List<Map<String, Object>> users = userMapper.getUserByUserAccount(username, password);
        if(users != null && users.size() > 0){
            String id = String.valueOf(users.get(0).get("user_id"));
            String token = jwtUtils.getToken(id, username);
            outPutObject.setUserKey(token);
            outPutObject.setBeans(users);
        }else {
            outPutObject.setReturnCode("-9999");
            outPutObject.setReturnMessage("账号或密码不正确");
        }
        return outPutObject;
    }


}