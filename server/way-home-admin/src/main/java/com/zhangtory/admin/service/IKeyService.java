package com.zhangtory.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhangtory.admin.model.request.AddKeyRequest;
import com.zhangtory.admin.model.vo.KeyInfoVO;

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
    IPage<KeyInfoVO> queryKeys(Long current);

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
