package com.zhangtory.admin.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author zhangtory
 * @date 2020/6/22 22:29
 * @description: 用户登录请求类
 */
@Data
@ApiModel("用户登录请求类")
public class LoginRequest {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 16, message = "用户名长度需要4到16个字符")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String password;

}
