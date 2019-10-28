package com.zhangtory.wayhome.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/28 16:58
 * @Description:
 */
@Data
public class AddressDto {

    private Long id;
    private String appId;
    private String protocol;
    private String ip;
    private Integer port;
    private String innerProtocol;
    private String innerIp;
    private String innerPort;
    private Date createTime;
    private Date updateTime;

}
