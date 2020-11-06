package com.zhangtory.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author zhangtory
 * @date 2020/6/23 20:38
 * @description: 用户实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WhUser {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String mobile;

    private String email;

}
