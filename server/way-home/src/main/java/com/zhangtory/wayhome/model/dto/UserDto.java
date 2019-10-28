package com.zhangtory.wayhome.model.dto;

import lombok.Data;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/28 16:52
 * @Description:
 */
@Data
public class UserDto {

    private Long userId;
    private String username;
    private String email;
    private String mobile;

}
