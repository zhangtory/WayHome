package com.zhangtory.wayhome.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:38
 * @Description:
 */
@Data
public class SetWayHomeReq {

    /**
     * 公网使用协议
     * default: http
     */
    private String protocol = "http";

    /**
     * 公网转发端口
     * default: 8123
     */
    private Integer port = 8123;

    /**
     * 内网使用协议
     * default: http
     */
    private String innerProtocol = "http";

    /**
     * 内网提供服务的ip地址
     */
    @NotBlank(message = "内网服务器地址不能为空")
    private String innerIp;

    /**
     * 内网监听端口
     * default: 8123
     */
    private Integer innerPort = 8123;

    /**
     * 时间戳，防重放攻击
     */
    @NotNull(message = "时间戳不能为空")
    private Long timestamp;

    /**
     * 签名，防止恶意调用
     */
    @NotBlank(message = "签名不能为空")
    private String sign;

}
