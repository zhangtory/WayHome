package com.zhangtory.wayhomecore.component;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: redis辅助工具
 */
@Component
public class RedisHelper {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认超时时间为1天
     */
    private static final long DEFAULT_TIMEOUT = 24 * 60 * 60;

    /**
     * 设置简单缓存
     * @param key
     * @param value
     * @param time
     */
    public void set(String key, Object value, long time) {
        if (time <= 0L) {
            time = DEFAULT_TIMEOUT;
        }
        redisTemplate.opsForValue().set(key, value, time + getRandomSeconds(), TimeUnit.SECONDS);
    }

    /**
     * 设置简单缓存
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_TIMEOUT);
    }

    /**
     * 获取对象
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取随机时间，防止缓存雪崩
     * 随机10秒到180秒
     * @return
     */
    private long getRandomSeconds() {
        return RandomUtils.nextLong(10, 180);
    }

}
