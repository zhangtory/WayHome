package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.dao.AddressRepository;
import com.zhangtory.wayhome.dao.UserKeyRepository;
import com.zhangtory.wayhome.entity.Address;
import com.zhangtory.wayhome.entity.UserKey;
import com.zhangtory.wayhome.exception.SignErrorException;
import com.zhangtory.wayhome.model.request.SetWayHomeReq;
import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.utils.BeanUtils;
import com.zhangtory.wayhome.model.HomeAddrCache;
import com.zhangtory.wayhome.utils.IpUtils;
import com.zhangtory.wayhome.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:47
 * @Description:
 */
@Service
@Slf4j
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private HomeAddrCache homeAddrCache;

    @Autowired
    private UserKeyRepository userKeyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String getWayHome(String appID, HttpServletRequest request) {
        return findWayHome(IpUtils.getIpAddr(request));
    }

    @Override
    @Transactional
    public void setHomeAddr(String appID, SetWayHomeReq req, HttpServletRequest request) {
        // 验签，防止恶意调用及攻击
        UserKey userKey = userKeyRepository.getByAppId(appID);
        if (userKey == null) {
            throw new SignErrorException("appID错误");
        }
        if (!SignUtils.checkSign(BeanUtils.objectToMap(req), userKey.getSecretKey(), req.getSign())) {
            throw new SignErrorException("签名错误");
        }
        // 记录信息
        String ip = IpUtils.getIpAddr(request);
        Address address = addressRepository.getByAppId(appID);
        if (address == null) {
            address = new Address();
            address.setAppId(appID);
        }
        address.setIp(ip);
        BeanUtils.copyProperties(req, address);
        addressRepository.save(address);
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
                    .append(homeAddrCache.getProtocol()).append("://")
                    .append(homeAddrCache.getIpAddr()).append(":")
                    .append(homeAddrCache.getPort());
            return addr.toString();
        }
    }

}
