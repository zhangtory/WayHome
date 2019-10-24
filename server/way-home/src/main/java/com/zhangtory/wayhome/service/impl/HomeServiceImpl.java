package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.exception.SignErrorException;
import com.zhangtory.wayhome.model.SetWayHomeReq;
import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.utils.BeanUtils;
import com.zhangtory.wayhome.model.HomeAddrCache;
import com.zhangtory.wayhome.utils.IpUtils;
import com.zhangtory.wayhome.utils.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:47
 * @Description:
 */
@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private HomeAddrCache homeAddrCache;
    @Value("${secretKey}")
    private String secretKey;

    @Override
    public String getWayHome(HttpServletRequest request) {
        return findWayHome(IpUtils.getIpAddr(request));
    }

    @Override
    public void setHomeAddr(SetWayHomeReq req, HttpServletRequest request) {
        // 验签，防止恶意调用及攻击
        if (!SignUtils.checkSign(BeanUtils.objectToMap(req), secretKey, req.getSign())) {
            throw new SignErrorException("签名错误");
        }
        if (homeAddrCache.getTimestamp() != null && req.getTimestamp() <= homeAddrCache.getTimestamp()) {
            throw new SignErrorException("时间戳错误");
        }
        // 记录信息
        recordHomeInfo(req, IpUtils.getIpAddr(request));
    }

    /**
     * 通过请求的ip地址判断返回公网还是内网地址
     * @param requestIp
     * @return
     */
    private String findWayHome(String requestIp) {
        // 如果没有地址，返回默认地址
        if (StringUtils.isEmpty(homeAddrCache.getIpAddr())) {
            return "https://zhangyaoyu.com";
        }
        if (requestIp.equals(homeAddrCache.getIpAddr())) {
            // 请求来自同一个ip，返回内网地址
            StringBuilder addr = new StringBuilder()
                    .append(homeAddrCache.getInnerProtocol()).append("://")
                    .append(homeAddrCache.getInnerIpAddr()).append(":")
                    .append(homeAddrCache.getInnerPort());
            return addr.toString();
        } else {
            // 返回外网地址
            StringBuilder addr = new StringBuilder()
                    .append(homeAddrCache.getPort()).append("://")
                    .append(homeAddrCache.getIpAddr()).append(":")
                    .append(homeAddrCache.getPort());
            return addr.toString();
        }
    }

    /**
     * 设置新的地址信息
     * @param req
     * @param ipAddr
     */
    private void recordHomeInfo(SetWayHomeReq req, String ipAddr) {
        homeAddrCache.setProtocol(req.getProtocol());
        homeAddrCache.setIpAddr(ipAddr);
        homeAddrCache.setPort(req.getPort());
        homeAddrCache.setInnerProtocol(req.getInnerProtocol());
        homeAddrCache.setInnerIpAddr(req.getInnerIp());
        homeAddrCache.setInnerPort(req.getInnerPort());
        homeAddrCache.setTimestamp(req.getTimestamp());
    }

}
