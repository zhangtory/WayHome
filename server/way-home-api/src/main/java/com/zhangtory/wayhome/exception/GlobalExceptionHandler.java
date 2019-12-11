package com.zhangtory.wayhome.exception;

import com.zhangtory.wayhome.constant.CodeConstant;
import com.zhangtory.wayhome.constant.ExceptionConstant;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangYaoYu
 * @date 2019/12/7 20:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public BaseResponse userException(UserException e) {
        return BaseResponseBuilder.failure(e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public BaseResponse bindException(BindException e) {
        return BaseResponseBuilder.failure(CodeConstant.VALID_ERROR_MSG, e.getBindingResult().getAllErrors());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<Map<String, String>> errList = new ArrayList<>();
        errors.forEach(err -> {
            Map<String, String> errMap = new HashMap<>(4);
            errMap.put("errMsg", err.getDefaultMessage());
            DefaultMessageSourceResolvable source = (DefaultMessageSourceResolvable) err.getArguments()[0];
            errMap.put("filed", source.getDefaultMessage());
            errList.add(errMap);
        });
        return BaseResponseBuilder.failure(errList);
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse globalExceptionHandler(Exception e) {
        e.printStackTrace();
        return BaseResponseBuilder.failure(ExceptionConstant.SYSTEM_BUSY);
    }

}
