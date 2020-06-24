package com.zhangtory.wayhomeadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangtory.wayhomeadmin.component.RedisHelper;
import com.zhangtory.wayhomeadmin.config.UserContext;
import com.zhangtory.wayhomeadmin.constant.RedisKeyConstant;
import com.zhangtory.wayhomeadmin.constant.ResultCode;
import com.zhangtory.wayhomeadmin.exception.KeyException;
import com.zhangtory.wayhomeadmin.mapper.KeyMapper;
import com.zhangtory.wayhomeadmin.model.entity.Key;
import com.zhangtory.wayhomeadmin.model.request.ApplyKeyRequest;
import com.zhangtory.wayhomeadmin.model.response.KeyResponse;
import com.zhangtory.wayhomeadmin.service.IKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: zhangtory
 * @date: 6/24 15:37
 * @description: 钥匙相关
 */
@Service
@Slf4j
public class KeyServiceImpl implements IKeyService {

    @Autowired
    private KeyMapper keyMapper;

    @Autowired
    private RedisHelper redisHelper;

    /**
     * 获取当前用户的钥匙列表
     *
     * @return
     */
    @Override
    public List<KeyResponse> queryUserKeys() {
        String username = UserContext.getUsername();
        QueryWrapper<Key> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username)
                .orderByDesc("create_time");
        List<Key> keys = keyMapper.selectList(wrapper);
        List<KeyResponse> responses = new ArrayList<>(keys.size());
        keys.forEach(key -> {
            KeyResponse response = new KeyResponse();
            BeanUtils.copyProperties(key, response);
            responses.add(response);
        });
        return responses;
    }

    /**
     * 申请钥匙
     *
     * @param request
     */
    @Override
    public void applyKey(ApplyKeyRequest request) {
        String username = UserContext.getUsername();
        Key key = new Key();
        key.setKeyName(request.getKeyName());
        key.setUsername(username);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        key.setSecretKey(uuid);
        keyMapper.insert(key);
    }

    /**
     * 删除钥匙
     *
     * @param id
     */
    @Override
    public void deleteKey(Long id) {
        // 需要验证当前用户是否持有该钥匙
        String username = UserContext.getUsername();
        QueryWrapper<Key> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("username", username);
        Key key = keyMapper.selectOne(wrapper);
        if (key == null) {
            throw new KeyException(ResultCode.KEY_NOT_FOUND);
        }
        String keyName = key.getKeyName();
        keyMapper.deleteById(id);
        // 删除缓存
        redisHelper.delete(RedisKeyConstant.KEY_ADDRESS_REDIS_KEY_PREFIX + username + ":" + keyName);
    }
}
