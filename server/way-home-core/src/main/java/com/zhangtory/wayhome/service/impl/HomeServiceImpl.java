package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.constant.KeyResultCode;
import com.zhangtory.wayhome.constant.KeyStatus;
import com.zhangtory.wayhome.exception.KeyException;
import com.zhangtory.wayhome.model.request.SetAddressRequest;
import com.zhangtory.wayhome.model.response.AddressResponse;
import com.zhangtory.wayhome.model.vo.KeyAddressVO;
import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.service.IKeyAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author zhangtory
 * @date 2020/6/22 21:53
 * @description: 地址相关业务
 */
@Service
@Slf4j
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private IKeyAddressService keyAddressService;

    /**
     * 获取对应地址信息
     * @param username 用户名
     * @param keyName 钥匙名
     * @return 地址信息
     */
    @Override
    public AddressResponse getAddress(String username, String keyName) {
        AddressResponse response = new AddressResponse();
        KeyAddressVO keyAddress = keyAddressService.getKeyAddress(username, keyName);
        if (StringUtils.isEmpty(keyAddress.getIp())) {
            BeanUtils.copyProperties(keyAddress, response);
            // 构建完整url
            StringBuilder url = new StringBuilder();
            if (!StringUtils.isEmpty(keyAddress.getProtocol())) {
                url.append(keyAddress.getProtocol()).append("://");
            }
            url.append(keyAddress.getIp());
            if (keyAddress.getPort() != null) {
                url.append(":").append(keyAddress.getPort());
            }
            if (!StringUtils.isEmpty(keyAddress.getPath())) {
                url.append(keyAddress.getPath());
            }
            response.setUrl(url.toString());
        }
        return response;
    }

    /**
     * 设置地址信息
     *
     * @param ip   客户端真实ip
     * @param addr 上报的地址信息
     */
    @Override
    public void setAddress(String ip, SetAddressRequest addr) {
        KeyAddressVO keyAddress = keyAddressService.getKeyAddress(addr.getUsername(), addr.getKeyName());
        // 检查钥匙是否可用
        boolean available = checkKeyAvailable(keyAddress);
        if (!available) {
            throw new KeyException(KeyResultCode.KEY_CAN_NOT_USE);
        }
        /**
         * 如果地址有变动则修改缓存。
         * 如果地址没有变动，需要刷新缓存ttl，防止过期后信息丢失。
         */
        if (!checkEquals(ip, addr, keyAddress)) {
            keyAddressService.saveKeyAddress(keyAddress);
        } else {
            keyAddressService.updateExpire(keyAddress.getUsername(), keyAddress.getKeyName());
        }
    }

    /**
     * 检查钥匙的可用性
     *  1.检查是否存在该钥匙
     *  2.钥匙状态是否可用
     * @param keyAddress
     */
    public boolean checkKeyAvailable(KeyAddressVO keyAddress) {
        return !StringUtils.isEmpty(keyAddress.getSecretKey())
                && KeyStatus.AVAILABLE.getStatus().equals(keyAddress.getStatus());
    }

    /**
     * 检查地址是否有变动
     * @param ip
     * @param req
     * @param key
     * @return
     */
    public boolean checkEquals(String ip, SetAddressRequest req, KeyAddressVO key) {
        return ip.equals(key.getIp())
                && req.getProtocol().equals(key.getProtocol())
                && req.getPort().equals(key.getPort())
                && req.getPath().equals(key.getPath());
    }

}
