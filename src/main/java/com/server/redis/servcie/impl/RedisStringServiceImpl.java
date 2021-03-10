package com.server.redis.servcie.impl;

import com.server.redis.servcie.RedisStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisStringServiceImpl implements RedisStringService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(String key, String Value) {
        stringRedisTemplate.opsForValue().set(key, Value);
    }

    @Override
    public boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public void update(String key, String Value) {
        stringRedisTemplate.opsForValue().set(key, Value);
    }

    @Override
    public boolean exist(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public Long ttl(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    @Override
    public void expire(String key, String value, Long times) {
        stringRedisTemplate.opsForValue().set(key, value, times, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 对Value有要求，数字类型!
     *
     * @param key
     * @return
     */
    @Override
    public Long increNum(String key) {
        if (stringRedisTemplate.hasKey(key)) {
            return stringRedisTemplate.opsForValue().increment(key, 1L);
        } else {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(1L));
            return 1L;
        }
    }
}
