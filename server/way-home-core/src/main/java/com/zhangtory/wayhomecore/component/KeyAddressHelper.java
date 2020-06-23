package com.zhangtory.wayhomecore.component;

import com.zhangtory.wayhomecore.constant.KeyStatus;
import com.zhangtory.wayhomecore.constant.ResultCode;
import com.zhangtory.wayhomecore.exception.KeyException;
import com.zhangtory.wayhomecore.model.dto.KeyAddressDTO;
import com.zhangtory.wayhomecore.model.entity.Key;
import com.zhangtory.wayhomecore.model.request.SetAddressRequest;
import org.springframework.util.StringUtils;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: 钥匙地址信息辅助工具
 */
public class KeyAddressHelper {

    /**
     * 通过数据库key实体构建钥匙地址信息对象
     * @param key
     * @return
     */
    public static KeyAddressDTO buildByKey(Key key) {
        KeyAddressDTO keyAddress = new KeyAddressDTO();
        if (key == null) {
            return keyAddress;
        }
        // 设置秘钥信息
        keyAddress.setUsername(key.getUsername());
        keyAddress.setKeyName(key.getKeyName());
        keyAddress.setSecretKey(key.getSecretKey());
        keyAddress.setStatus(key.getStatus());
        return keyAddress;
    }

    /**
     * 检查钥匙的可用性
     * @param keyAddress
     */
    public static void checkKey(KeyAddressDTO keyAddress) {
        // 1.检查是否存在该钥匙
        if (StringUtils.isEmpty(keyAddress.getSecretKey())) {
            throw new KeyException(ResultCode.KEY_NOT_FOUND);
        }
        // 2.钥匙状态是否可用
        if (KeyStatus.DISABLED.getStatus().equals(keyAddress.getStatus())) {
            throw new KeyException(ResultCode.KEY_DISABLED);
        }
    }

    /**
     * 检查地址是否有变动
     * @param ip
     * @param req
     * @param key
     * @return
     */
    public static boolean checkEquals(String ip, SetAddressRequest req, KeyAddressDTO key) {
        if (!ip.equals(key.getIp())) {
            return true;
        }
        if (!req.getProtocol().equals(key.getProtocol())) {
            return true;
        }
        if (!req.getPort().equals(key.getPort())) {
            return true;
        }
        if (!req.getPath().equals(key.getPath())) {
            return true;
        }
        return false;
    }

    /**
     * 复制地址信息
     * @param ip
     * @param req
     * @param key
     * @return
     */
    public static KeyAddressDTO copyAddress(String ip, SetAddressRequest req, KeyAddressDTO key) {
        key.setProtocol(req.getProtocol());
        key.setIp(ip);
        key.setPort(req.getPort());
        key.setPath(req.getPath());
        return key;
    }

}
