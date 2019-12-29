package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.config.UserContext;
import com.zhangtory.wayhome.entity.Key;
import com.zhangtory.wayhome.enums.KeyDelEnum;
import com.zhangtory.wayhome.mapper.KeyMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.utils.EncryptUtils;
import com.zhangtory.wayhome.utils.JwtUtils;
import com.zhangtory.wayhome.utils.SignUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangtory
 * @since 2019-12-01
 */
@Service
public class KeyServiceImpl extends ServiceImpl<KeyMapper, Key> implements IKeyService {

    @Override
    public Key addKey() {
        Key key = new Key();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        key.setKeyId(uuid);
        String secretKey = EncryptUtils.md5(Math.random() + uuid);
        key.setSecretKey(secretKey);
        key.setUserId(UserContext.getUserId());
        this.save(key);
        key = this.getById(uuid);
        return key;
    }

    @Override
    public List<Key> queryKeys() {
        List<Key> keyList = this.lambdaQuery().eq(Key::getDel, KeyDelEnum.UnDelete.getDel()).eq(Key::getUserId, UserContext.getUserId()).orderByAsc(Key::getCreateTime).list();
        return keyList;
    }

    @Override
    public void deleteKey(String keyId) {
        Key key = getById(keyId);
        key.setDel(KeyDelEnum.Deleted.getDel());
        this.updateById(key);
    }
}
