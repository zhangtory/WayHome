package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.request.SetWayHomeReq;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:47
 * @Description:
 */
public interface IHomeService {

    /**
     * 获取家的跳转地址
     * @return
     */
    String getWayHome(String appID, HttpServletRequest request);

    /**
     * 设置家的地址
     * @param request
     */
    void setHomeAddr(String appID, SetWayHomeReq req, HttpServletRequest request);

}
