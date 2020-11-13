package com.zhangtory.admin.constant;

import com.zhangtory.core.constant.CommonResult;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 17:39
 * @Description:
 */
public class UserResult extends CommonResult {

    /**
     * 两次密码不一致
     */
    public static final UserResult RE_PASSWORD_NOT_SAME = new UserResult(-1, "re_password_not_same");

    /**
     * 用户已存在
     */
    public static final UserResult USER_EXISTS = new UserResult(-2, "user_exists");

    /**
     * 用户不存在
     */
    public static final UserResult USER_NOT_EXISTS = new UserResult(-3, "user_not_exists");

    /**
     * 用户或密码错误
     */
    public static final UserResult USER_OR_PASSWORD_ERROR = new UserResult(-4, "user_or_password_error");

    /**
     * 旧密码错误
     */
    public static final UserResult OLD_PASSWORD_ERROR = new UserResult(-5, "old_password_error");

    /**
     * 重置密码的secret无效
     */
    public static final UserResult ACCOUNT_FIND_SECRET_ERROR = new UserResult(-6, "account_find_secret_error");


    public UserResult(Integer code, String message) {
        super(code, message);
    }
}
