package com.coder.rental.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 设置值
     * @param key 键
     * @param value 值
     * @param timeout 超时时间
     */
    public void set(String key,String value,Long timeout){
        stringRedisTemplate.opsForValue().set(key,value,timeout,
                TimeUnit.SECONDS);
    }
    /**
     * 获取值
     * @param key 键
     * @return 值
     */
    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
    /**
     * 删除
     * @param key 键
     */
    public void del(String key){
        stringRedisTemplate.delete(key);
    }
}
