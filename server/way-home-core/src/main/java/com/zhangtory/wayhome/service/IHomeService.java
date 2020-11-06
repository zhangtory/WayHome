package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.request.SetAddressRequest;
import com.zhangtory.wayhome.model.response.AddressResponse;

/**
 * @author zhangtory
 * @date 2020/6/22 21:53
 * @description: 地址相关业务
 */
public interface IHomeService {

    /**
     * 获取对应地址信息
     * @param username 用户名
     * @param keyName 钥匙名
     * @return 地址信息
     */
    AddressResponse getAddress(String username, String keyName);

    /**
     * 设置地址信息
     * @param ip 客户端真实ip
     * @param addr 上报的地址信息
     */
    void setAddress(String ip, SetAddressRequest addr);
    
}
