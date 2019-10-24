package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.utils.HomeAddrCache;
import com.zhangtory.wayhome.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:47
 * @Description:
 */
@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private HomeAddrCache homeAddrCache;
    @Value("${ha.protocol}")
    private String protocol;
    @Value("${ha.port}")
    private String port;

    @Override
    public String getHomeAddr() {
        String ipAddr = homeAddrCache.getIpAddr();
        if (StringUtils.isEmpty(ipAddr)) {
            return "https://zhangyaoyu.com";
        }
        StringBuilder redirectAddr = new StringBuilder(protocol).append("://").append(ipAddr).append(":").append(port);
        return redirectAddr.toString();
    }

    @Override
    public void setHomeAddr(HttpServletRequest request) {
        // TODO 验签，防止恶意调用及攻击

        homeAddrCache.setIpAddr(IpUtils.getIpAddr(request));
    }

}
