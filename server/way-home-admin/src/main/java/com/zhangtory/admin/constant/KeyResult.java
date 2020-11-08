package com.zhangtory.admin.constant;

import com.zhangtory.core.constant.CommonResult;

/**
 * @author zhangtory
 * @date 2020/11/8 17:11
 * @description:
 */
public class KeyResult extends CommonResult {

    public static final CommonResult KEY_EXISTS = new CommonResult(-1, "key_exists");
    public static final CommonResult KEY_NOT_FOUND = new CommonResult(-2, "key_not_found");

    public KeyResult(Integer code, String message) {
        super(code, message);
    }
}
