package com.zhangtory.wayhome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangtory.wayhome.constant.ExceptionConstant;
import com.zhangtory.wayhome.entity.Key;
import com.zhangtory.wayhome.exception.AddressException;
import com.zhangtory.wayhome.mapper.KeyMapper;
import com.zhangtory.wayhome.service.IHomeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYaoYu
 * @date 2019/12/12 22:02
 */
@Service
@Slf4j
public class HomeServiceImpl extends ServiceImpl<KeyMapper, Key> implements IHomeService {

    @Override
    public String getAddress(String keyId) {
        Key key = this.getById(keyId);
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
}
