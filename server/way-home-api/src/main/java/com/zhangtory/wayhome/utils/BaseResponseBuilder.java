package com.zhangtory.wayhome.utils;

import com.zhangtory.wayhome.constant.CodeConstant;
import com.zhangtory.wayhome.model.response.BaseResponse;

public class BaseResponseBuilder {

    public static BaseResponse success() {
        return BaseResponse.builder().code(CodeConstant.SUCCESS_CODE).msg(CodeConstant.SUCCESS).build();
    }

    public static BaseResponse failure() {
        return BaseResponse.builder().code(CodeConstant.FAILURE_CODE).msg(CodeConstant.FAILURE).build();
    }

    public static BaseResponse success(String msg, Object result) {
        return BaseResponse.builder().code(CodeConstant.SUCCESS_CODE)
                .msg(msg).data(result).build();
    }

    public static BaseResponse failure(String msg, Object result) {
        return BaseResponse.builder().code(CodeConstant.FAILURE_CODE)
                .msg(msg).data(result).build();
    }

    public static BaseResponse success(Object result) {
        return BaseResponse.builder().code(CodeConstant.SUCCESS_CODE)
                .msg(CodeConstant.SUCCESS).data(result).build();
    }

    public static BaseResponse failure(Object result) {
        return BaseResponse.builder().code(CodeConstant.FAILURE_CODE)
                .msg(CodeConstant.FAILURE).data(result).build();
    }

    public static BaseResponse success(String msg) {
        return BaseResponse.builder().code(CodeConstant.SUCCESS_CODE)
                .msg(msg).build();
    }

    public static BaseResponse failure(String msg) {
        return BaseResponse.builder().code(CodeConstant.FAILURE_CODE)
                .msg(msg).build();
    }

}
