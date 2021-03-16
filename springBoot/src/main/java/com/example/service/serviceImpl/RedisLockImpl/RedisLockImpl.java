package com.example.service.serviceImpl.RedisLockImpl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.example.service.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisLockImpl implements RedisLock {
    @Autowired
    RedisUtils redisUtils;

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    @Override
    public boolean tryLock(String key, long timeOut, TimeUnit timeUnit){
        //通过ThreadLocal实现加锁解锁归一
        String uuid = UUID.randomUUID().toString();
        threadLocal.set(uuid);
        redisUtils.setNX(key, uuid, timeOut, timeUnit);
        return true;
    }
    @Override
    public void releaseLock(String key){
        String threadKey = threadLocal.get();
        if(threadKey != null && threadKey.equals(redisUtils.get(key))){
            threadLocal.remove();
            redisUtils.deleteKey(key);
        }
    }
}
