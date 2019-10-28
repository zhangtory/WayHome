package com.zhangtory.wayhome.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:22
 * @Description:
 */
@Data
@Entity
public class UserKey extends BaseEntity {

    private Long userId;
    private String appId;
    private String secretKey;
    @OneToOne
    @JoinColumn(insertable = false, name = "app_id")
    private Address address;

}
