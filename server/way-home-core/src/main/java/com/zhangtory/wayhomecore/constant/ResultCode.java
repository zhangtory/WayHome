package com.zhangtory.wayhomecore.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangtory
 * @date 2020/6/22 22:12
 * @description: 返回结果枚举
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    /**
     * 请求成功
     */
    SUCCESS(0, "success"),
    /**
     * 未知异常
     */
    FAILURE(-1, "system busy"),
    /**
     * 请求参数错误
     */
    REQUEST_PARAMS_ERROR(-10, "request params error");

    private int code;
    private String message;

}
