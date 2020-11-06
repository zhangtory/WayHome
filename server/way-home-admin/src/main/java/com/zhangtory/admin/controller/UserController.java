package com.zhangtory.admin.controller;

import com.zhangtory.admin.model.request.LoginRequest;
import com.zhangtory.admin.model.request.UserRegisterRequest;
import com.zhangtory.admin.service.IUserService;
import com.zhangtory.core.response.BaseResponse;
import com.zhangtory.core.response.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 17:29
 * @Description: 用户相关接口
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public BaseResponse register(@RequestBody @Valid UserRegisterRequest userRegisterRequest, HttpServletResponse response) {
        String token = userService.register(userRegisterRequest);
//        response.setHeader(, token);
        return ResponseBuilder.success(token);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResponse login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
        String token = userService.login(loginRequest);
//        response.setHeader(BaseConstant.TOKEN_HEADER, token);
        return ResponseBuilder.success(token);
    }

    @PostMapping("/find")
    @ApiOperation("找回密码")
    public BaseResponse findAccount() {
        // TODO 找回密码
        return ResponseBuilder.success();
    }

    @PostMapping("/reset")
    @ApiOperation("重置密码")
    public BaseResponse resetPassword() {
        // TODO 重置密码
        return ResponseBuilder.success();
    }

}
