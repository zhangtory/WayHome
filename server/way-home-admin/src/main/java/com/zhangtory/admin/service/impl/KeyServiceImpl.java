package com.zhangtory.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangtory.admin.componet.ShortUrlCreator;
import com.zhangtory.admin.constant.KeyResult;
import com.zhangtory.admin.constant.RedisKey;
import com.zhangtory.admin.dao.WhKeyMapper;
import com.zhangtory.admin.model.entity.WhKey;
import com.zhangtory.admin.model.request.AddKeyRequest;
import com.zhangtory.admin.model.vo.KeyAddressVO;
import com.zhangtory.admin.model.vo.KeyInfoVO;
import com.zhangtory.admin.service.IKeyService;
import com.zhangtory.core.exception.CommonException;
import com.zhangtory.jwt.component.UserContext;
import com.zhangtory.redis.service.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private ShortUrlCreator shortUrlCreator;

    @Value("${url.go}")
    private String goUrl;

    /**
     * 查询当前用户的key
     *
     * @param current 分页请求
     * @return
     */
    @Override
    public IPage<KeyInfoVO> queryKeys(Long current) {
        IPage<WhKey> keys = keyMapper.selectPage(new Page<>(current, 10),
                new QueryWrapper<WhKey>().lambda()
                        .eq(WhKey::getUsername, userContext.getUsername())
                        .orderByDesc(WhKey::getCreateTime));
        // 转换为返回对象
        IPage<KeyInfoVO> keyInfoVOIPage = new Page<>(keys.getCurrent(), keys.getSize(), keys.getTotal());
        keyInfoVOIPage.setPages(keys.getPages());
        List<WhKey> records = keys.getRecords();
        List<KeyInfoVO> keyInfoVOList = new ArrayList<>();
        records.forEach(record -> {
            KeyInfoVO info = new KeyInfoVO();
            BeanUtils.copyProperties(record, info);
            // 从redis获取url
            KeyAddressVO keyAddress = getAddressKeyInCache(record.getUsername(), record.getKeyName());
            if (keyAddress == null || StringUtils.isEmpty(keyAddress.getIp())) {
                info.setUrl("未上报，请下载客户端。");
            } else {
                info.setUrl(buildUrl(keyAddress));
            }
            keyInfoVOList.add(info);
        });
        keyInfoVOIPage.setRecords(keyInfoVOList);
        return keyInfoVOIPage;
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
        // 拼凑url，创建短链
        String url = goUrl.replace("{username}", key.getUsername()).replace("{keyName}", key.getKeyName());
        String shortUrl = shortUrlCreator.getShortUrl(url);
        key.setShortUrl(shortUrl);
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
        String redisKey = RedisKey.KEY_ADDRESS_REDIS_KEY_PREFIX
                .replace("${username}", username).replace("${keyName}", keyName);
        redisHelper.delete(redisKey);
    }

    /**
     * 从缓存中查询钥匙地址
     * @param username
     * @param keyName
     * @return
     */
    private KeyAddressVO getAddressKeyInCache(String username, String keyName) {
        String redisKey = RedisKey.KEY_ADDRESS_REDIS_KEY_PREFIX
                .replace("${username}", username).replace("${keyName}", keyName);
        KeyAddressVO keyAddressVO = redisHelper.get(redisKey, KeyAddressVO.class);
        return keyAddressVO;
    }

    /**
     * 构建完整url
     * @param keyAddress
     * @return
     */
    private String buildUrl(KeyAddressVO keyAddress) {
        StringBuilder url = new StringBuilder();
        if (!StringUtils.isEmpty(keyAddress.getProtocol())) {
            url.append(keyAddress.getProtocol()).append("://");
        }
        url.append(keyAddress.getIp());
        if (keyAddress.getPort() != null) {
            url.append(":").append(keyAddress.getPort());
        }
        if (!StringUtils.isEmpty(keyAddress.getPath())) {
            url.append(keyAddress.getPath());
        }
        return url.toString();
    }
}
