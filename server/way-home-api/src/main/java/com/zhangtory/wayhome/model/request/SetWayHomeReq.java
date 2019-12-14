package com.zhangtory.wayhome.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ZhangYaoYu
 * @date 2019/12/14 12:49
 */
@Data
public class SetWayHomeReq {

    /**
     * keyId
     */
    @NotBlank(message = "KeyId不能为空")
    private String keyId;

    /**
     * 访问协议
     */
    private String protocol;

    /**
     * 访问端口
     */
    private Integer port;

    /**
     * 访问参数
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
