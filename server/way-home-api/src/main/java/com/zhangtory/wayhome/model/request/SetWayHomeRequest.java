package com.zhangtory.wayhome.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ZhangYaoYu
 * @date 2019/12/14 12:49
 */
@Data
@ApiModel(description = "地址上报请求类")
public class SetWayHomeRequest {

    /**
     * keyId
     */
    @NotBlank(message = "KeyId不能为空")
    @ApiModelProperty(value = "KeyId", required = true)
    private String keyId;

    /**
     * 访问协议
     */
    @ApiModelProperty(value = "访问协议", required = false)
    private String protocol;

    /**
     * 访问端口
     */
    @ApiModelProperty(value = "访问端口", required = false)
    private Integer port;

    /**
     * 访问参数
     */
    @ApiModelProperty(value = "访问参数", required = false)
    private String path;

    /**
     * 时间戳，防重放攻击
     */
    @NotNull(message = "时间戳不能为空")
    @ApiModelProperty(value = "时间戳", required = true)
    private Long timestamp;

    /**
     * 签名，防止恶意调用
     */
    @NotBlank(message = "签名不能为空")
    @ApiModelProperty(value = "签名", required = true)
    private String sign;

}
