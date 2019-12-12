package com.zhangtory.wayhome.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ZhangYaoYu
 * @date 2019/12/12 21:45
 */
@Data
public class ResetPasswordReq {

    @NotBlank(message = "旧密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String newPassword;

    @NotBlank(message = "重复密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String reNewPassword;

}
