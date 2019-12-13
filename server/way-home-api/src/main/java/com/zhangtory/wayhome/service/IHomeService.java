package com.zhangtory.wayhome.service;

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

}
