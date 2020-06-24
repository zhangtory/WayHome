package com.zhangtory.wayhomeadmin.service;

import com.zhangtory.wayhomeadmin.model.request.ApplyKeyRequest;
import com.zhangtory.wayhomeadmin.model.response.KeyResponse;

import java.util.List;

/**
 * @author: zhangtory
 * @date: 6/24 15:37
 * @description: 钥匙相关
 */
public interface IKeyService {

    /**
     * 获取当前用户的钥匙列表
     * @return
     */
    List<KeyResponse> queryUserKeys();

    /**
     * 申请钥匙
     * @param request
     */
    void applyKey(ApplyKeyRequest request);

    /**
     * 删除钥匙
     * @param id
     */
    void deleteKey(Long id);

}
