package com.zhangtory.admin.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 13:39
 * @Description:
 */
@Data
@ApiModel("邮件找回密码请求方法")
public class AccountFindSendMailRequest {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

}
