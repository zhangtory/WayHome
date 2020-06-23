package com.zhangtory.wayhomeadmin.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhangtory
 * @date 2020/6/22 22:29
 * @description: 统一请求类
 */
@Data
public class BaseRequest {

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
