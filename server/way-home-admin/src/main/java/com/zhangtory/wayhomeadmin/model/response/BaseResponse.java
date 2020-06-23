package com.zhangtory.wayhomeadmin.model.response;

import com.zhangtory.wayhomeadmin.constant.ResultCode;
import lombok.Getter;

/**
 * @author: zhangtory
 * @date: 10/25 17:27
 * @description: 统一返回对象
 */
@Getter
public class BaseResponse<T> {

    private int code;

    private String message;

    private T data;

    public BaseResponse(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public BaseResponse(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

}
