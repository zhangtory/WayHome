package com.zhangtory.wayhomeadmin.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author zhangtory
 * @date 2020/6/22 22:29
 * @description: 用户登录请求类
 */
@Data
public class LoginRequest {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 16, message = "用户名长度需要4到16个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String password;

}
