package com.zhangtory.wayhomeadmin.exception;

import com.zhangtory.wayhomeadmin.constant.ResultCode;
import lombok.Data;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: 用户自定义异常
 */
@Data
public class UserException extends RuntimeException {

    private ResultCode resultCode;

    public UserException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
