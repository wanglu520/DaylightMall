package com.example.service.serviceImpl.RedisLockImpl;

import java.util.concurrent.TimeUnit;

public interface RedisLock {
    //加锁
    boolean tryLock(String key, long timeOut, TimeUnit timeUnit);
    //解锁
    void releaseLock(String key);
}
