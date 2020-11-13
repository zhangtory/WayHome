package com.zhangtory.admin.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 15:03
 * @Description:
 */
@Data
@ApiModel("用户找回密码-重置密码请求")
public class AccountFindRequest {

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度在4到16个字符之间")
    private String password;

}
