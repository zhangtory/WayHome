package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.request.SetWayHomeRequest;

/**
 * @author ZhangYaoYu
 * @date 2019/12/12 22:02
 */
public interface IHomeService {

    /**
     * 获取跳转地址
     * @param keyId
     * @return
     */
    String getAddress(String keyId);

    /**
     * 设置地址
     * @param setWayHomeRequest
     * @param ip
     */
    void setAddress(SetWayHomeRequest setWayHomeRequest, String ip);

}
