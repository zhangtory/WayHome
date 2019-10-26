package com.zhangtory.wayhome.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:23
 * @Description:
 */
@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false, updatable = false)
    private Date createTime;
    @Column(insertable = false)
    private Date updateTime;

}
