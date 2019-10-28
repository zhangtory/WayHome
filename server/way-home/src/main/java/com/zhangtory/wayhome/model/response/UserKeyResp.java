package com.zhangtory.wayhome.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserKeyResp {

    private Long id;
    private Long userId;
    private String appId;
    private String secretKey;
    private Date createTime;
    private Date updateTime;

}
