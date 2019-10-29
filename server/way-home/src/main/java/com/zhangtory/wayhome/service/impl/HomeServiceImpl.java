package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.dao.AddressRepository;
import com.zhangtory.wayhome.dao.UserKeyRepository;
import com.zhangtory.wayhome.entity.Address;
import com.zhangtory.wayhome.entity.UserKey;
import com.zhangtory.wayhome.exception.SignErrorException;
import com.zhangtory.wayhome.exception.UrlNotFundException;
import com.zhangtory.wayhome.model.request.SetWayHomeReq;
import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.utils.BeanUtils;
import com.zhangtory.wayhome.utils.IpUtils;
import com.zhangtory.wayhome.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private UserKeyRepository userKeyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String getWayHome(String url, HttpServletRequest request) {
        UserKey userKey = userKeyRepository.getByUrl(url);
        if (userKey == null) {
            throw new UrlNotFundException("没有该url");
        }
        Address address = addressRepository.getByAppId(userKey.getAppId());
        String reqIp = IpUtils.getIpAddr(request);
        StringBuffer ret = new StringBuffer();
        if (reqIp.equals(address.getIp())) {
            // 请求ip来自家中内网，返回内网信息
            ret.append(address.getInnerProtocol())
                    .append("://")
                    .append(address.getInnerIp())
                    .append(":")
                    .append(address.getInnerPort());
        } else {
            // 返回外网信息
            ret.append(address.getProtocol())
                    .append("://")
                    .append(address.getIp())
                    .append(":")
                    .append(address.getPort());
        }
        return ret.toString();
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

}
