package com.zhangtory.admin.config;

import com.zhangtory.admin.constant.UserResult;
import com.zhangtory.core.response.BaseResponse;
import com.zhangtory.core.response.ResponseBuilder;
import com.zhangtory.jwt.exception.LoginCheckException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangtory
 * @date 2020/11/15 14:53
 * @description:
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = LoginCheckException.class)
    public BaseResponse commonException(LoginCheckException e) {
        return ResponseBuilder.failure(UserResult.USER_NOT_LOGIN);
    }

}
