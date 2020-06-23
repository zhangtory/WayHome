package com.zhangtory.wayhomecore.component;

import com.zhangtory.wayhomecore.constant.ResultCode;
import com.zhangtory.wayhomecore.model.response.BaseResponse;

/**
 * @author zhangtory
 * @date 2020/6/22 22:05
 * @description: 统一返回包装
 */
public class BaseResponseBuilder {

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(ResultCode.SUCCESS);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(ResultCode.SUCCESS, data);
    }

    public static <T> BaseResponse<T> success(ResultCode resultCode, T data) {
        return new BaseResponse<>(resultCode, data);
    }

    public static <T> BaseResponse<T> failure() {
        return new BaseResponse<>(ResultCode.FAILURE);
    }

    public static <T> BaseResponse<T> failure(ResultCode resultCode) {
        return new BaseResponse<>(resultCode);
    }

    public static <T> BaseResponse<T> failure(ResultCode resultCode, T data) {
        return new BaseResponse<>(resultCode, data);
    }

}
