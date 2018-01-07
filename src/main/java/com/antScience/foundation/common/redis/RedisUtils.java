package com.antScience.foundation.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by lirui on 2017/11/8.
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtils {

    public static final long THIRTY_MINUTES = 60 * 30L;
    public static final long FIVE_MINUTES = 60 * 5L;

    @Autowired
    private RedisTemplate redisTemplate;

    public void setForCode(String key, String value) {
        ValueOperations<Serializable, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        redisTemplate.expire(key, FIVE_MINUTES, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        ValueOperations<Serializable, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
}
