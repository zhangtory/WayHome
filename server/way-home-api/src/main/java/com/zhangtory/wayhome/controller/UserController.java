package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.constant.CodeConstant;
import com.zhangtory.wayhome.model.request.LoginReq;
import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IUserService;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author ZhangYaoYu
 * @date 2019/12/7 20:35
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user")
    public BaseResponse register(@RequestBody @Valid UserRegisterReq userRegisterReq) {
        userService.register(userRegisterReq);
        return BaseResponseBuilder.success();
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody @Valid LoginReq loginReq, HttpServletResponse response) {
        String token = userService.login(loginReq);
        response.setHeader(CodeConstant.TOKEN_HEADER, token);
        return BaseResponseBuilder.success(CodeConstant.SUCCESS_MSG, token);
    }

}
