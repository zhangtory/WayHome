package com.zhangtory.wayhomecore.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangtory
 * @date 2020/6/22 22:39
 * @description: 基础实体
 */
@Data
public class BaseEntity {

    private Date createTime;

    private Date updateTime;

}
