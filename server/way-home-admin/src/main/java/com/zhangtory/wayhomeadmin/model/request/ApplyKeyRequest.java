package com.zhangtory.wayhomeadmin.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author: zhangtory
 * @date: 6/24 15:56
 * @description: 申请钥匙请求
 */
@Data
public class ApplyKeyRequest {

    @NotBlank(message = "钥匙名不能为空")
    @Length(min = 1, max = 32, message = "钥匙名只允许1到32个字符")
    private String keyName;

}
