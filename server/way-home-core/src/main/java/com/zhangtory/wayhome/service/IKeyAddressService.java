package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.vo.KeyAddressVO;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 10:41
 * @Description: 用户Key相关接口
 */
public interface IKeyAddressService {

    /**
     * 获取钥匙及地址信息
     * @param username
     * @param keyName
     * @return
     */
    KeyAddressVO getKeyAddress(String username, String keyName);

    /**
     * 缓存钥匙地址信息
     * @param keyAddress
     */
    void saveKeyAddress(KeyAddressVO keyAddress);

    /**
     * 更新缓存的过期时间
     * @param username
     * @param keyName
     */
    void updateExpire(String username, String keyName);

}
