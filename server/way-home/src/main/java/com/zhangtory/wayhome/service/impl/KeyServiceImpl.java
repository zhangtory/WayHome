package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.dao.UserKeyRepository;
import com.zhangtory.wayhome.dao.UserRepository;
import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.entity.UserKey;
import com.zhangtory.wayhome.exception.MaxAppCountException;
import com.zhangtory.wayhome.model.response.UserKeyResp;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.utils.SignUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class KeyServiceImpl implements IKeyService {

    @Autowired
    private UserKeyRepository userKeyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserKeyResp applyUserKey(String username) {
        UserKeyResp userKeyResp = new UserKeyResp();

        User user = userRepository.getByUsername(username);
        // 每个用户限制5个appId
        Long count = userKeyRepository.countByUserId(user.getId());
        if (count >= 5) {
            throw new MaxAppCountException("超出用户最大appId数量限制");
        }
        UserKey userKey = new UserKey();
        userKey.setUserId(user.getId());
        String uuid = UUID.randomUUID().toString();
        userKey.setAppId(uuid);
        String secretKey = SignUtils.md5(Math.random() + uuid);
        userKey.setSecretKey(secretKey);
        userKey = userKeyRepository.save(userKey);

        BeanUtils.copyProperties(userKey, userKeyResp);
        return userKeyResp;
    }

    @Override
    public void deleteUserKey(String username, String appId) {
        System.out.println("111");
        User user = userRepository.getByUsername(username);
        userKeyRepository.deleteByUserIdAndAppId(user.getId(), appId);
    }

    @Override
    public List<UserKeyResp> queryAllUserKey(String username) {
        List<UserKeyResp> userKeyRespList = new ArrayList<>();
        User user = userRepository.getByUsername(username);
        BeanUtils.copyProperties(user.getUserKeyList(), userKeyRespList);
        return userKeyRespList;
    }
}