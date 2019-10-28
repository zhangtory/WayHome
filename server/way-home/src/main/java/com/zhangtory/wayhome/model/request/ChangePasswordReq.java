package com.zhangtory.wayhome.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/28 15:12
 * @Description:
 */
@Data
public class ChangePasswordReq {

    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
    @NotBlank(message = "重复密码不能为空")
    private String reNewPassword;

}
