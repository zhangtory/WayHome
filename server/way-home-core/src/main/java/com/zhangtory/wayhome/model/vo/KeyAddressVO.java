package com.zhangtory.wayhome.model.vo;

import lombok.Data;

/**
 * @author: zhangtory
 * @date: 10/25 17:27
 * @description: 保存钥匙、地址信息
 */
@Data
public class KeyAddressVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 钥匙名
     */
    private String keyName;

    /**
     * 签名秘钥
     */
    private String secretKey;

    /**
     * 短链
     */
    private String shortUrl;

    /**
     * 钥匙状态
     */
    private Integer status;

    /**
     * 访问协议
     */
    private String protocol;

    /**
     * 客户端真实ip地址
     */
    private String ip;

    /**
     * 访问端口
     */
    private Integer port;

    /**
     * 访问路径参数
     */
    private String path;

}
