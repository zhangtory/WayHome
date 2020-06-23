package com.zhangtory.wayhomecore.constant;

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
    AVAILABLE(1),
    DISABLED(0);

    private Integer status;
}
