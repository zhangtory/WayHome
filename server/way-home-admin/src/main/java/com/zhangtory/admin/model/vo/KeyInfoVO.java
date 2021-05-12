package com.zhangtory.admin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 15:24
 * @Description: 返回的钥匙信息
 */
@Data
@ApiModel("返回的钥匙信息")
public class KeyInfoVO {

    @ApiModelProperty("keyID")
    private Long id;

    @ApiModelProperty("短链接")
    private String shortUrl;

    @ApiModelProperty("钥匙名")
    private String keyName;

    @ApiModelProperty("目标url")
    private String url;

    @ApiModelProperty("签名秘钥")
    private String secretKey;

    @ApiModelProperty("是否启用：1启用；0禁用")
    private Integer status;

    @ApiModelProperty("钥匙创建时间")
    private Date createTime;

}
