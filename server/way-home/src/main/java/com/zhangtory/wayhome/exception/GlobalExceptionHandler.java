package com.zhangtory.wayhome.exception;

import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse globalExceptionHandler(RuntimeException e) {
        return BaseResponseBuilder.failure(e.getMessage());
    }

}
