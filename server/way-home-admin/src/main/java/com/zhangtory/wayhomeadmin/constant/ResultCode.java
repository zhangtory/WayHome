package com.zhangtory.wayhomeadmin.constant;

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
    FAILURE(-1, "failure"),
    /**
     * 请求参数错误
     */
    REQUEST_PARAMS_ERROR(-10, "请求参数错误"),
    /**
     * 时间戳错误
     */
    TIMESTAMP_ERROR(-11,"时间戳错误"),
    /**
     * 签名错误
     */
    SIGN_ERROR(-12, "签名错误"),
    /**
     * 未找到该钥匙
     */
    KEY_NOT_FOUND(-101, "未找到该钥匙"),
    /**
     * 钥匙已被禁用
     */
    KEY_DISABLED(-102, "钥匙已被禁用");

    private int code;
    private String message;

}
