package com.example.service.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.example.service.OutPutObject;
import com.example.service.Utils.JwtUtils;
import com.example.service.Utils.RedisUtils;
import com.example.service.dao.User;
import com.example.service.mapper.UserMapper;
import com.example.service.serviceImpl.Impl.BaseServiceImpl;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.example.service.serviceImpl.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseServiceImpl {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    int i =0;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired(required=false)
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtils redisUtils;

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
    @PostMapping(value = "/seckill")
    public OutPutObject seckill(@RequestBody Map body){
        OutPutObject out = getOutPutObject();
        String offerId = (String) body.get("offerId");
        //加分布式锁
        String key = "offerId_"+offerId;
        Boolean isLocked = redisUtils.setNX(key, "locking", 3L, TimeUnit.SECONDS);
        if(!isLocked){
            out.setReturnMessage("fail");
            return out;
        }
        try {
            int stock = getStock(redisUtils.get(offerId));
            if(stock > 0){
                stock -= 1;
                redisUtils.set("123",stock);
                out.setReturnMessage("sucess");
                logger.info("库存扣减成功，当前库存为：{}", stock);
            }else {
                out.setReturnMessage("isEmpty");
                logger.error("库存不足，扣减库存失败商品id：{}", offerId);
            }
        }finally {
            //解锁
            redisUtils.deleteKey(key);
        }
        return out;
    }
    public Integer getStock(Object obj){
        int res = 0;
        try {
            if(obj != null){
                res = (int) obj;
            }
            return res;
        }catch (Exception e){
            logger.error("金额转换异常",e);
            return res;
        }
    }

}