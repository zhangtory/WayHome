package com.zhangtory.wayhome.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/28 16:56
 * @Description:
 */
@Data
public class UserKeyDto {

    private Long id;
    private Long userId;
    private String appId;
    private String secretKey;
    private Date createTime;
    private Date updateTime;
    private List<AddressDto> addressList;

}
