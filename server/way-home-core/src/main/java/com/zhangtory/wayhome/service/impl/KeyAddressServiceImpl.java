package com.zhangtory.wayhome.service.impl;

import com.zhangtory.redis.contant.RedisTimeConstant;
import com.zhangtory.redis.service.RedisHelper;
import com.zhangtory.wayhome.dao.KeyMapper;
import com.zhangtory.wayhome.model.entity.Key;
import com.zhangtory.wayhome.model.vo.KeyAddressVO;
import com.zhangtory.wayhome.service.IKeyAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 10:44
 * @Description: 用户Key相关接口
 */
@Service
@Slf4j
public class KeyAddressServiceImpl implements IKeyAddressService {

    @Autowired
    private KeyMapper keyMapper;

    @Autowired
    private RedisHelper redisHelper;

    /**
     * keyAddress信息前缀
     * wayhome:key:{username}:{keyName}
     */
    public static final String KEY_ADDRESS_REDIS_KEY_PREFIX = "wayhome:key:${username}:${keyName}";

    /**
     * 获取钥匙及地址信息
     *
     * @param username
     * @param keyName
     * @return
     */
    @Override
    public KeyAddressVO getKeyAddress(String username, String keyName) {
        String redisKey = KEY_ADDRESS_REDIS_KEY_PREFIX
                .replace("${username}", username).replace("${keyName}", keyName);
        KeyAddressVO keyAddressVO = redisHelper.get(redisKey, KeyAddressVO.class);
        if (keyAddressVO == null) {
            // 从数据库查询key信息
            Key key = keyMapper.getByUsernameAndKeyName(username, keyName);
            // 不论是否有key，都构建对象放入缓存，防止缓存穿透
            keyAddressVO = buildByKey(key);
            redisHelper.set(redisKey, keyAddressVO, RedisTimeConstant.ONE_DAY);
        }
        return keyAddressVO;
    }

    /**
     * 缓存钥匙地址信息
     *
     * @param keyAddress
     */
    @Override
    public void saveKeyAddress(KeyAddressVO keyAddress) {
        String redisKey = KEY_ADDRESS_REDIS_KEY_PREFIX
                .replace("${username}", keyAddress.getUsername()).replace("${keyName}", keyAddress.getKeyName());
        redisHelper.set(redisKey, keyAddress, RedisTimeConstant.ONE_DAY);
    }

    /**
     * 更新缓存的过期时间
     *
     * @param username
     * @param keyName
     */
    @Override
    public void updateExpire(String username, String keyName) {
        String redisKey = KEY_ADDRESS_REDIS_KEY_PREFIX
                .replace("${username}", username).replace("${keyName}", keyName);
        redisHelper.setExpire(redisKey, RedisTimeConstant.ONE_DAY);
    }

    /**
     * 通过数据库key实体构建钥匙地址信息对象
     * @param key
     * @return
     */
    private KeyAddressVO buildByKey(Key key) {
        KeyAddressVO keyAddress = new KeyAddressVO();
        if (key == null) {
            return keyAddress;
        }
        // 设置秘钥信息
        keyAddress.setUsername(key.getUsername());
        keyAddress.setKeyName(key.getKeyName());
        keyAddress.setSecretKey(key.getSecretKey());
        keyAddress.setStatus(key.getStatus());
        keyAddress.setShortUrl(key.getShortUrl());
        return keyAddress;
    }

}
