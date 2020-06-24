package com.zhangtory.wayhomeadmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhangtory
 * @date 2020/6/23 20:38
 * @description: 用户实体
 */
@Data
@TableName("wh_user")
public class User extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String mobile;

    private String email;

}
