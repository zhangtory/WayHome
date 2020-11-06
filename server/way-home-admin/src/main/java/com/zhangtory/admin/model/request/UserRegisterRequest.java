package com.zhangtory.admin.model.request;

import com.zhangtory.admin.model.entity.WhUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author zhangtory
 * @date 2020/6/22 22:29
 * @description: 用户注册请求类
 */
@Data
@ApiModel("用户注册请求类")
public class UserRegisterRequest {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 16, message = "用户名长度需要4到16个字符")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String password;

    @ApiModelProperty("重复密码")
    @NotBlank(message = "重复密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String rePassword;

    @ApiModelProperty("邮箱")
    @Email(message = "请输入正确的邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("手机号码")
    @Pattern(regexp = "^1[345678]\\d{9}", message = "请输入正确的手机号码")
    private String mobile;

    /**
     * 转化为WhUser
     * @return
     */
    public WhUser getWhUser() {
        WhUser user = new WhUser();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setMobile(this.mobile);
        return user;
    }

}
