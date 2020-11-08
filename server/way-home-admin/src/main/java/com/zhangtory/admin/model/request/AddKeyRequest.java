package com.zhangtory.admin.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhangtory
 * @date 2020/11/8 16:36
 * @description: 添加钥匙
 */
@Data
@ApiModel("添加钥匙请求类")
public class AddKeyRequest {

    @ApiModelProperty("钥匙名称")
    @NotBlank(message = "钥匙名不能为空")
    private String keyName;

}
