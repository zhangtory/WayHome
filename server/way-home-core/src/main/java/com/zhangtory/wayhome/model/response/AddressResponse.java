package com.zhangtory.wayhome.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: zhangtory
 * @date: 10/25 17:27
 * @description: 地址查询返回对象
 */
@Data
@ApiModel("地址查询返回对象")
public class AddressResponse {

    @ApiModelProperty("协议")
    private String protocol;

    @ApiModelProperty("IP")
    private String ip;

    @ApiModelProperty("端口")
    private Integer port;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("完整url")
    private String url;

    @ApiModelProperty("短链")
    private String shortUrl;

}
