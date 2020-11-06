package com.zhangtory.wayhome.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author: zhangtory
 * @date: 10/25 17:27
 * @description: 地址信息设置的请求对象
 */
@Data
@ApiModel("地址信息设置的请求对象")
public class SetAddressRequest {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("钥匙名")
    @NotBlank(message = "钥匙名不能为空")
    private String keyName;

    @ApiModelProperty("访问协议")
    private String protocol;

    @ApiModelProperty("访问端口")
    private Integer port;

    @ApiModelProperty("访问路径参数")
    private String path;

    @ApiModelProperty("毫秒时间戳")
    @NotNull(message = "时间戳不能为空")
    private Long timestamp;

    @ApiModelProperty("签名")
    @NotBlank(message = "签名不能为空")
    private String sign;

}
