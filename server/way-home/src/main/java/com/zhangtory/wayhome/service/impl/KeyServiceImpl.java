package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.dao.UserKeyRepository;
import com.zhangtory.wayhome.dao.UserRepository;
import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.entity.UserKey;
import com.zhangtory.wayhome.exception.MaxAppCountException;
import com.zhangtory.wayhome.exception.UserExistsException;
import com.zhangtory.wayhome.model.response.UserKeyResp;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.utils.BeanUtils;
import com.zhangtory.wayhome.utils.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class KeyServiceImpl implements IKeyService {

    @Autowired
    private UserKeyRepository userKeyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
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
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userKey.setAppId(uuid);
        String secretKey = SignUtils.md5(Math.random() + uuid);
        userKey.setSecretKey(secretKey);
        userKey = userKeyRepository.save(userKey);
        BeanUtils.copyProperties(userKey, userKeyResp);
        return userKeyResp;
    }

    @Override
    @Transactional
    public void deleteUserKey(String username, String appId) {
        User user = userRepository.getByUsername(username);
        UserKey userKey = userKeyRepository.getByUserIdAndAppId(user.getId(), appId);
        if (userKey == null) {
            throw new UserExistsException("appId删除失败");
        }
        userKey.setDel(1);
        userKeyRepository.save(userKey);
    }

    @Override
    public List<UserKeyResp> queryAllUserKey(String username) {
        User user = userRepository.getByUsername(username);
        List<UserKeyResp> userKeyRespList = BeanUtils.copyListProperties(user.getUserKeyList(), UserKeyResp.class);
        return userKeyRespList;
    }

}
