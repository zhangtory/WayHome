package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.entity.Key;
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

}
