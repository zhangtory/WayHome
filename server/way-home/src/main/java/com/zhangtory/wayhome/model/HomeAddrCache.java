package com.zhangtory.wayhome.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:21
 * @Description:
 */
@Data
@Component
public class HomeAddrCache {

    /**
     * 公网使用协议
     * http/https
     */
    private String protocol;

    /**
     * 公网ip
     */
    private String ipAddr;

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
     * 时间戳，防重放攻击
     */
    private Long timestamp;

}
