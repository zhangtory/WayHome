package com.zhangtory.wayhome.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:28
 * @Description:
 */
@Data
public class UserRegisterReq {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "密码不能为空")
    private String repassword;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "手机号不能为空")
    private String mobile;

}
