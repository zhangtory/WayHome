package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.entity.Key;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangtory
 * @since 2019-12-01
 */
public interface IKeyService extends IService<Key> {

    /**
     * 申请新key
     * @return
     */
    Key addKey();

    /**
     * 查询key
     * @return
     */
    List<Key> queryKeys();

    /**
     * 删除key
     * @param keyId
     */
    void deleteKey(String keyId);

    /**
     * 从缓存中获取key
     * @param keyId
     * @return
     */
    Key getKeyInCache(String keyId);

    /**
     * 将key保存到缓存
     * @param key
     */
    void saveKeyToCache(Key key);

}
