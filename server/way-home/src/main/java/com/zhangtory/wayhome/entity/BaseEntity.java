package com.zhangtory.wayhome.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:23
 * @Description:
 */
@Data
@Entity
public class BaseEntity {

    private Long id;
    private Date createTime;
    private Date updateTime;

}
