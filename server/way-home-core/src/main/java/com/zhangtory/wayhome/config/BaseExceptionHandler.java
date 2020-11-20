package com.zhangtory.wayhome.config;

import com.zhangtory.core.constant.CommonResult;
import com.zhangtory.core.response.BaseResponse;
import com.zhangtory.core.response.ResponseBuilder;
import com.zhangtory.sign.exception.SignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangtory
 * @date 2020/11/19 22:21
 * @description:
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = SignException.class)
    public BaseResponse commonException(SignException e) {
        return ResponseBuilder.failure(CommonResult.SIGN_ERROR);
    }

}
