package com.zhangtory.wayhome.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:28
 * @Description:
 */
@Data
public class UserRegisterRequest {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 16, message = "用户名长度需要4到16个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String password;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String repassword;

    @Email(message = "请输入正确的邮箱")
    private String email;

    @Pattern(regexp = "^1[345678]\\d{9}", message = "请输入正确的手机号码")
    private String mobile;

}
