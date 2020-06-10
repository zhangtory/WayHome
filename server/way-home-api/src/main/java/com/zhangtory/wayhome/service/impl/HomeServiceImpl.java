package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.constant.ExceptionConstant;
import com.zhangtory.wayhome.exception.AddressException;
import com.zhangtory.wayhome.exception.KeyException;
import com.zhangtory.wayhome.model.entity.Key;
import com.zhangtory.wayhome.model.request.SetWayHomeRequest;
import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.utils.BeanUtils;
import com.zhangtory.wayhome.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYaoYu
 * @date 2019/12/12 22:02
 */
@Service
@Slf4j
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private IKeyService keyService;

    @Override
    public String getAddress(String keyId) {
        Key key = keyService.getKeyInCache(keyId);
        StringBuffer address = new StringBuffer();
        if (key != null) {
            if (StringUtils.isEmpty(key.getIp())) {
                throw new AddressException(ExceptionConstant.ADDRESS_NOT_INIT);
            }
            // 构建地址
            if (StringUtils.isNotEmpty(key.getProtocol())) {
                address.append(key.getProtocol()).append("://");
            }
            address.append(key.getIp());
            if (key.getPort() != null) {
                address.append(":").append(key.getPort());
            }
            if (StringUtils.isNotEmpty(key.getPath())) {
                address.append(key.getPath());
            }
        } else {
            throw new AddressException(ExceptionConstant.ADDRESS_NOT_EXIST);
        }
        return address.toString();
    }

    @Override
    public void setAddress(SetWayHomeRequest request, String ip) {
        Key key = keyService.getKeyInCache(request.getKeyId());
        // 检查时间戳是否过期
        if (!SignUtils.checkTimestamp(request.getTimestamp())) {
            throw new KeyException(ExceptionConstant.TIMESTAMP_ERROR);
        }
        // 检查签名
        if (!SignUtils.checkSign(BeanUtils.objectToMap(request), key.getSecretKey(), request.getSign())) {
            throw new KeyException(ExceptionConstant.SIGN_ERROR);
        }
        // 如果数据有变动，则记录信息
        if (checkChange(key, request, ip)) {
            keyService.saveKeyToCache(key);
        }
    }

    /**
     * 判断数据是否有变动
     * @param key
     * @param req
     * @return
     */
    private boolean checkChange(Key key, SetWayHomeRequest req, String ip) {
        /**
         * 可能不止1个数据有变化
         */
        boolean flag = false;
        if (!req.getProtocol().equals(key.getProtocol())) {
            key.setProtocol(req.getProtocol());
            flag = true;
        }
        if (!ip.equals(key.getIp())) {
            key.setIp(ip);
            flag = true;
        }
        if (!req.getPort().equals(key.getPort())) {
            key.setPort(req.getPort());
            flag = true;
        }
        if (!req.getPath().equals(key.getPath())) {
            key.setPath(req.getPath());
            flag = true;
        }
        return flag;
    }

}
