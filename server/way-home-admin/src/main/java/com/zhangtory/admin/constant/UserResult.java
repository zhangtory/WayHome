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
     * 用户或密码错误
     */
    public static final UserResult USER_OR_PASSWORD_ERROR = new UserResult(-3, "user_or_password_error");


    public UserResult(Integer code, String message) {
        super(code, message);
    }
}
