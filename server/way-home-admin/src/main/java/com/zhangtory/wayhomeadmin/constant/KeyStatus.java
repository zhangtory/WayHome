package com.zhangtory.wayhomeadmin.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: 钥匙状态枚举
 */
@Getter
@AllArgsConstructor
public enum KeyStatus {
    /**
     * 可用
     */
    AVAILABLE(1),
    /**
     * 禁用
     */
    DISABLED(0);

    private Integer status;
}
