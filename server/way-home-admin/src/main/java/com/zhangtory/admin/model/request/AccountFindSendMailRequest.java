package com.zhangtory.admin.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 13:39
 * @Description:
 */
@Data
@ApiModel("邮件找回密码请求方法")
public class AccountFindSendMailRequest {

    @ApiModelProperty("用户邮箱")
    @Email(message = "请输入正确的邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

}
