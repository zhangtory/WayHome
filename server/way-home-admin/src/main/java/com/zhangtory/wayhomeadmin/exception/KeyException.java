package com.zhangtory.wayhomeadmin.exception;

import com.zhangtory.wayhomeadmin.constant.ResultCode;
import lombok.Data;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: 钥匙自定义异常
 */
@Data
public class KeyException extends RuntimeException {

    private ResultCode resultCode;

    public KeyException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
