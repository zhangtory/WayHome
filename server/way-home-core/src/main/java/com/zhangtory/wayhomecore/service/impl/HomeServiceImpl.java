package com.zhangtory.wayhomecore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhangtory.wayhomecore.component.KeyAddressHelper;
import com.zhangtory.wayhomecore.component.RedisHelper;
import com.zhangtory.wayhomecore.constant.RedisKeyConstant;
import com.zhangtory.wayhomecore.constant.ResultCode;
import com.zhangtory.wayhomecore.exception.KeyException;
import com.zhangtory.wayhomecore.mapper.KeyMapper;
import com.zhangtory.wayhomecore.model.dto.KeyAddressDTO;
import com.zhangtory.wayhomecore.model.entity.Key;
import com.zhangtory.wayhomecore.model.request.SetAddressRequest;
import com.zhangtory.wayhomecore.model.response.AddressResponse;
import com.zhangtory.wayhomecore.service.IHomeService;
import com.zhangtory.wayhomecore.utils.BeanUtils;
import com.zhangtory.wayhomecore.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangtory
 * @date 2020/6/22 21:53
 * @description: 地址相关业务
 */
@Service
@Slf4j
public class HomeServiceImpl implements IHomeService {
    
    @Autowired
    private KeyMapper keyMapper;

    @Autowired
    private RedisHelper redisHelper;

    /**
     * 获取对应地址信息
     * @param username 用户名
     * @param keyName 钥匙名
     * @return 地址信息
     */
    @Override
    public AddressResponse getAddress(String username, String keyName) {
        KeyAddressDTO keyAddress = getKeyAddressInCache(username, keyName);
        AddressResponse addressResponse = KeyAddressHelper.buildAddressResponse(keyAddress);
        return addressResponse;
    }

    /**
     * 设置地址信息
     *
     * @param ip   客户端真实ip
     * @param addr 上报的地址信息
     */
    @Override
    public void setAddress(String ip, SetAddressRequest addr) {
        // 1.检查时间戳是否过期
        if (!SignUtils.checkTimestamp(addr.getTimestamp())) {
            throw new KeyException(ResultCode.TIMESTAMP_ERROR);
        }
        // 2.从缓存中获取钥匙地址信息
        KeyAddressDTO keyAddress = getKeyAddressInCache(addr.getUsername(), addr.getKeyName());
        // 3.检查签名
        if (!SignUtils.checkSign(BeanUtils.objectToMap(addr), keyAddress.getSecretKey(), addr.getSign())) {
            throw new KeyException(ResultCode.SIGN_ERROR);
        }
        // 4.检查该钥匙是否可用
        KeyAddressHelper.checkKey(keyAddress);
        // 5.判断地址信息是否有变动,如果有变动则修改缓存数据
        if (KeyAddressHelper.checkEquals(ip, addr, keyAddress)) {
            saveKeyAddressToCache(KeyAddressHelper.copyAddress(ip, addr, keyAddress));
        }
    }

    /**
     * 获取缓存中的钥匙地址信息
     * @param username 用户名
     * @param keyName 钥匙名
     * @return
     */
    private KeyAddressDTO getKeyAddressInCache(String username, String keyName) {
        String redisKey = RedisKeyConstant.KEY_ADDRESS_REDIS_KEY_PREFIX + username + ":" + keyName;
        Object o = redisHelper.get(redisKey);
        if (o == null) {
            // 缓存不存在，从数据库查询钥匙信息
            Key key = keyMapper.getByUsernameAndKeyName();
            // 不论是否有key，都构建对象放入缓存，防止缓存穿透
            KeyAddressDTO keyAddress = KeyAddressHelper.buildByKey(key);
            redisHelper.set(redisKey, JSONObject.toJSONString(keyAddress));
            return keyAddress;
        } else {
            return JSONObject.parseObject(o.toString(), KeyAddressDTO.class);
        }
    }

    /**
     * 更新缓存中的地址信息
     * @param keyAddress
     */
    private void saveKeyAddressToCache(KeyAddressDTO keyAddress) {
        String redisKey = RedisKeyConstant.KEY_ADDRESS_REDIS_KEY_PREFIX + keyAddress.getUsername() + ":" + keyAddress.getKeyName();
        redisHelper.set(redisKey, JSONObject.toJSONString(keyAddress));
    }

}
