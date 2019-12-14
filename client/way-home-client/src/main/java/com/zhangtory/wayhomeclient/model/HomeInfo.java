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
     * 服务器API地址
     */
    private String serverUrl;

    /**
     * 访问协议
     */
    private String protocol;

    /**
     * 访问端口
     */
    private Long port;

    /**
     * 访问参数
     */
    private String path;

    /**
     * Keyd
     */
    private String keyId;

    /**
     * 秘钥
     */
    private String secretKey;

}
