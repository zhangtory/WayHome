package com.zhangtory.wayhome.exception;

import com.zhangtory.wayhome.constant.CodeConstant;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public BaseResponse bindException(BindException e) {
        return BaseResponseBuilder.failure(CodeConstant.VALID_ERROR_MSG, e.getBindingResult().getAllErrors());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse globalExceptionHandler(Exception e) {
        return BaseResponseBuilder.failure(e.getMessage());
    }

}
