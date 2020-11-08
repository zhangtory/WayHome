package com.zhangtory.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.KeySequence;
import lombok.*;

/**
 * @Author: ZhangTory
 * @Date: 2020-11-08
 * @Description: 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WhKey implements Serializable {

    /**
     * keyID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 签名秘钥
     */
    private String secretKey;

    /**
     * 用户名
     */
    private String username;

    /**
     * 钥匙名
     */
    private String keyName;

    /**
     * 是否启用：1启用；0禁用
     */
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
