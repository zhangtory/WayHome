package com.zhangtory.wayhomeadmin.model.response;

import lombok.Data;

import java.util.Date;

/**
 * @author: zhangtory
 * @date: 6/24 15:38
 * @description: 钥匙信息返回对象
 */
@Data
public class KeyResponse {

    private Long id;

    private String secretKey;

    private String username;

    private String keyName;

    private Integer status;

    private Date createTime;

}
