package com.zhangtory.wayhome.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangtory
 * @since 2019-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wh_key")
public class Key implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * keyID
     */
    @TableId
    private String keyId;

    /**
     * 秘钥
     */
    private String secretKey;

    /**
     * 使用协议
     */
    private String protocol;

    /**
     * IPv4地址
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 是否删除：0未删除；1已删除
     */
    private Integer del;

    private LocalDateTime createTime;

    @TableField(update = "now()")
    private LocalDateTime updateTime;


}
