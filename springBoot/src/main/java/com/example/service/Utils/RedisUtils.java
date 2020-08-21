package com.example.service.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * redis工具类
 */
public final class RedisUtils {
    Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            logger.error("redis缓存写入异常:", e);
            return false;
        }
    }

    /**
     * 写入缓存同时设置失效时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime){
        boolean bool = set(key, value);
        if(bool){
            return expire(key, expireTime);
        }
        return bool;
    }

    /**
     * 写入ListMap类型的缓存
     * @param key
     * @param listMap
     * @return
     */
    public boolean setListMap(final String key, List<Map<String, Object>>listMap){
        try{
            redisTemplate.opsForList().leftPush(key, listMap);
            return true;
        }catch (Exception e){
            logger.error("redis写入缓存setForListMap异常：", e);
            return false;
        }
    }

    /**
     * 写入ListMap类型的缓存,并设置失效时间
     * @param key
     * @param listMap
     * @param expireTime
     * @return
     */
    public boolean setListMap(final String key, List<Map<String, Object>>listMap, Long expireTime){
        boolean bool = setListMap(key, listMap);
        if(bool){
            return expire(key, expireTime);
        }
        return bool;
    }

    /**
     * 写入Map类型的缓存
     * @param key
     * @param map
     * @return
     */
    public boolean setMap(final String key, Map<String, Object>map){
        try{
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            logger.error("", e);
            return false;
        }
    }

    /**
     * 写入Map类型的缓存,并设置失效时间
     * @param key
     * @param map
     * @param expireTime
     * @return
     */
    public boolean setMap(final String key, Map<String, Object>map, Long expireTime){
        boolean bool = setMap(key, map);
        if(bool){
            return expire(key, expireTime);
        }
        return bool;
    }

    /**
     * 设置key的失效时间
     * @param key
     * @param expireTime
     * @return
     */
    public boolean expire(final String key, Long expireTime){
        try {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            logger.error("reids失效时间设置异常:", e);
            return false;
        }
    }

    /**
     * 获取缓存数据,返回object
     * @param key
     * @return
     */
    @Nullable
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存数据,返回List<Map<String, Object>>
     * @param key
     * @return
     */
    @Nullable
    public List<Map<String, Object>> getListForObject(String key){
        Object object = get(key);
        if(object instanceof List<?>){
            return (List<Map<String,Object>>) object;
        }else {
            return null;
        }
    }

    /**
     * 获取缓存数据,返回Map<String, Object>
     * @param key
     * @return
     */
    @Nullable
    public Map<String, Object> getMapForObject(String key){
        Object object = get(key);
        if(object instanceof Map){
            return (Map<String, Object>)object;
        }else {
            return null;
        }
    }

    /**
     * 获取缓存数据,返回List<Map<String, Object>>
     * @param key
     * @return
     */
    @Nullable
    public List<Map<String, Object>> getListMap(String key){
        return (List<Map<String, Object>>) redisTemplate.opsForList().leftPop(key);
    }
    public Map<Object, Object> getMap(String key){
        return redisTemplate.opsForHash().entries(key);
    }
}
