package com.zhangtory.wayhomecore.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author: zhangtory
 * @date: 10/25 17:27
 * @description: 地址信息设置的请求对象
 */
@Data
public class SetAddressRequest {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 钥匙名
     */
    @NotBlank(message = "钥匙名不能为空")
    private String keyName;

    /**
     * 访问协议
     */
    private String protocol;

    /**
     * 访问端口
     */
    private Integer port;

    /**
     * 访问路径参数
     */
    private String path;

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
