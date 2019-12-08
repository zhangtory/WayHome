package com.zhangtory.wayhome.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ZhangYaoYu
 * @date 2019/12/8 20:24
 */
@Data
public class LoginReq {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 16, message = "用户名长度需要4到16个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String password;

}
