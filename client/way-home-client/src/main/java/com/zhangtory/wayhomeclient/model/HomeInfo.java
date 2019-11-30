package com.zhangtory.wayhomeclient.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 17:05
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "home")
@PropertySource("file:home.properties")
@Data
public class HomeInfo {

    /**
     * 公网使用协议
     * http/https
     */
    private String protocol;

    /**
     * 公网转发端口
     */
    private Long port;

    /**
     * 内网使用协议
     * http/https
     */
    private String innerProtocol;

    /**
     * 内网提供服务的ip地址
     */
    private String innerIpAddr;

    /**
     * 内网监听端口
     */
    private Long innerPort;

    /**
     * appId
     */
    private String appId;

    /**
     * 秘钥
     */
    private String secretKey;

}
