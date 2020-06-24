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
    KEY_DISABLED(-102, "钥匙已被禁用"),
    /**
     * 重复密码不一致
     */
    RE_PASSWORD_NOT_SAME(-201, "两次密码不一致"),
    /**
     * 用户已存在
     */
    USER_EXISTS(-202, "用户已存在"),
    /**
     * 用户或密码错误
     */
    USER_OR_PASSWORD_ERROR(-203, "用户或密码错误"),
    /**
     * 用户登录已过期
     */
    TOKEN_EXPIRED(-204, "用户登录已过期"),
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(-205, "用户未登录");

    private int code;
    private String message;

}
