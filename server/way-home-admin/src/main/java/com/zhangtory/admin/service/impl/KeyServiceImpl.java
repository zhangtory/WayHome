package com.zhangtory.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangtory.admin.constant.KeyResult;
import com.zhangtory.admin.dao.WhKeyMapper;
import com.zhangtory.admin.model.entity.WhKey;
import com.zhangtory.admin.model.request.AddKeyRequest;
import com.zhangtory.admin.service.IKeyService;
import com.zhangtory.core.exception.CommonException;
import com.zhangtory.jwt.component.UserContext;
import com.zhangtory.redis.service.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author zhangtory
 * @date 2020/11/8 15:56
 * @description: 钥匙相关service
 */
@Service
@Slf4j
public class KeyServiceImpl implements IKeyService {

    @Autowired
    private WhKeyMapper keyMapper;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private UserContext userContext;

    /**
     * keyAddress信息前缀
     * wayhome:key:{username}:{keyName}
     */
    public static final String KEY_ADDRESS_REDIS_KEY_PREFIX = "wayhome:key:${username}:${keyName}";

    /**
     * 查询当前用户的key
     *
     * @param current 分页请求
     * @return
     */
    @Override
    public IPage<WhKey> queryKeys(Long current) {
        IPage<WhKey> keys = keyMapper.selectPage(new Page<>(current, 10),
                new QueryWrapper<WhKey>().lambda()
                        .eq(WhKey::getUsername, userContext.getUsername())
                        .orderByDesc(WhKey::getCreateTime));
        return keys;
    }

    /**
     * 添加key
     *
     * @param request
     */
    @Override
    public void addKey(AddKeyRequest request) {
        WhKey key = new WhKey();
        key.setKeyName(request.getKeyName());
        key.setUsername(userContext.getUsername());
        key.setSecretKey(UUID.randomUUID().toString().replace("-", ""));
        try {
            keyMapper.insert(key);
        } catch (DuplicateKeyException e) {
            throw new CommonException(KeyResult.KEY_EXISTS);
        }
    }

    /**
     * 删除key
     *
     * @param id
     */
    @Override
    public void deleteKey(Long id) {
        // 需要验证当前用户是否持有该钥匙
        String username = userContext.getUsername();
        WhKey key = keyMapper.selectOne(new QueryWrapper<WhKey>().lambda()
                .eq(WhKey::getId, id)
                .eq(WhKey::getUsername, username));
        if (key == null) {
            throw new CommonException(KeyResult.KEY_NOT_FOUND);
        }
        String keyName = key.getKeyName();
        keyMapper.deleteById(id);
        // 删除缓存
        String redisKey = KEY_ADDRESS_REDIS_KEY_PREFIX
                .replace("${username}", username).replace("${keyName}", keyName);
        redisHelper.delete(redisKey);
    }
}
