package com.zhangtory.wayhome.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:47
 * @Description:
 */
public interface IHomeService {

    /**
     * 获取家的地址
     * @return
     */
    String getHomeAddr();

    /**
     * 设置家的地址
     * @param request
     */
    void setHomeAddr(HttpServletRequest request);

}
