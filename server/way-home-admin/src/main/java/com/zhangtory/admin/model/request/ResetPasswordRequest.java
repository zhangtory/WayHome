package com.zhangtory.admin.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 11:17
 * @Description: 用户密码重置请求类
 */
@Data
@ApiModel("用户密码重置请求类")
public class ResetPasswordRequest {

    @ApiModelProperty("旧密码")
    @NotBlank(message = "旧密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String oldPassword;

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String newPassword;

}
