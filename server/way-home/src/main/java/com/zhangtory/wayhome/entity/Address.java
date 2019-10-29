package com.zhangtory.wayhome.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:24
 * @Description:
 */
@Data
@Entity
public class Address extends BaseEntity {

    private String appId;
    private String protocol;
    private String ip;
    private Integer port;
    private String innerProtocol;
    private String innerIp;
    private Integer innerPort;

}
