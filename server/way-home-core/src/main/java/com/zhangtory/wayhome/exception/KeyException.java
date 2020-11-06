package com.zhangtory.wayhome.exception;

import com.zhangtory.core.constant.CommonResult;
import com.zhangtory.core.exception.CommonException;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: 钥匙自定义异常
 */
public class KeyException extends CommonException {

    public KeyException(CommonResult result) {
        super(result);
    }

}
