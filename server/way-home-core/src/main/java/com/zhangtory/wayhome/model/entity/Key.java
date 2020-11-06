package com.zhangtory.wayhome.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangtory
 * @date 2020/6/22 22:33
 * @description: wh_key
 */
@Data
@TableName("wh_key")
public class Key {

    @TableId(type = IdType.AUTO)
    private long id;

    private String secretKey;

    private String username;

    private String keyName;

    private int status;

    private Date createTime;

    private Date updateTime;

}
