package com.zhangtory.wayhome.model.response;

import com.zhangtory.wayhome.constant.CodeConstant;
import com.zhangtory.wayhome.model.response.BaseResponse;

/**
 * @author ZhangYaoYu
 * @date 2019/12/7 20:35
 */
public class BaseResponseBuilder {

    public static BaseResponse success() {
        return BaseResponse.builder().code(CodeConstant.SUCCESS_CODE).msg(CodeConstant.SUCCESS_MSG).build();
    }

    public static BaseResponse failure() {
        return BaseResponse.builder().code(CodeConstant.FAILURE_CODE).msg(CodeConstant.FAILURE_MSG).build();
    }

    public static BaseResponse success(Object result) {
        return BaseResponse.builder().code(CodeConstant.SUCCESS_CODE)
                .msg(CodeConstant.SUCCESS_MSG).data(result).build();
    }

    public static BaseResponse failure(Object result) {
        return BaseResponse.builder().code(CodeConstant.FAILURE_CODE)
                .msg(CodeConstant.FAILURE_MSG).data(result).build();
    }

    public static BaseResponse success(String msg) {
        return BaseResponse.builder().code(CodeConstant.SUCCESS_CODE)
                .msg(msg).build();
    }

    public static BaseResponse failure(String msg) {
        return BaseResponse.builder().code(CodeConstant.FAILURE_CODE)
                .msg(msg).build();
    }
    public static BaseResponse success(String msg, Object result) {
        return BaseResponse.builder().code(CodeConstant.SUCCESS_CODE)
                .msg(msg).data(result).build();
    }

    public static BaseResponse failure(String msg, Object result) {
        return BaseResponse.builder().code(CodeConstant.FAILURE_CODE)
                .msg(msg).data(result).build();
    }

}
