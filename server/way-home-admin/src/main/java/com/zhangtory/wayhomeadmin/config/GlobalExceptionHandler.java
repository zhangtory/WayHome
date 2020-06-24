package com.zhangtory.wayhomeadmin.config;

import com.zhangtory.wayhomeadmin.component.BaseResponseBuilder;
import com.zhangtory.wayhomeadmin.constant.ResultCode;
import com.zhangtory.wayhomeadmin.exception.KeyException;
import com.zhangtory.wayhomeadmin.exception.UserException;
import com.zhangtory.wayhomeadmin.model.response.BaseResponse;
import com.zhangtory.wayhomeadmin.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
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
 * @author zhangtory
 * @date 2019/12/7 20:35
 * @description: 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public BaseResponse bindException(BindException e) {
        return BaseResponseBuilder.failure(ResultCode.REQUEST_PARAMS_ERROR, e.getBindingResult().getAllErrors());
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
        return BaseResponseBuilder.failure(ResultCode.REQUEST_PARAMS_ERROR, errList);
    }

    @ExceptionHandler(value = KeyException.class)
    public BaseResponse keyException(KeyException e) {
        return BaseResponseBuilder.failure(e.getResultCode());
    }

    @ExceptionHandler(value = UserException.class)
    public BaseResponse userException(KeyException e) {
        return BaseResponseBuilder.failure(e.getResultCode());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse globalExceptionHandler(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return BaseResponseBuilder.failure();
    }

}
