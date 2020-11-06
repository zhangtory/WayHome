package com.zhangtory.wayhome.constant;

import com.zhangtory.core.constant.CommonResult;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 11:51
 * @Description:
 */
public class KeyResultCode extends CommonResult {

    public static final KeyResultCode KEY_CAN_NOT_USE = new KeyResultCode(-1, "key_can_not_use");

    public KeyResultCode(Integer code, String message) {
        super(code, message);
    }

}
