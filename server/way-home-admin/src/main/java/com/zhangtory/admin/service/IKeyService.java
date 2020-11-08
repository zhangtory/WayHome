package com.zhangtory.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhangtory.admin.model.entity.WhKey;
import com.zhangtory.admin.model.request.AddKeyRequest;

/**
 * @author zhangtory
 * @date 2020/11/8 15:55
 * @description: 钥匙相关service
 */
public interface IKeyService {

    /**
     * 查询当前用户的key
     * @param current
     * @return
     */
    IPage<WhKey> queryKeys(Long current);

    /**
     * 添加key
     * @param request
     */
    void addKey(AddKeyRequest request);

    /**
     * 删除key
     * @param id
     */
    void deleteKey(Long id);

}
