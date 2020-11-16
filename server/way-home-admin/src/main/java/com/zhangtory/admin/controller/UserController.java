package com.zhangtory.admin.controller;

import com.zhangtory.admin.model.request.*;
import com.zhangtory.admin.service.IUserService;
import com.zhangtory.core.response.BaseResponse;
import com.zhangtory.core.response.ResponseBuilder;
import com.zhangtory.jwt.constant.JwtConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        response.setHeader(JwtConstant.TOKEN_HEADER, token);
        return ResponseBuilder.success(token);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResponse login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
        String token = userService.login(loginRequest);
        response.setHeader(JwtConstant.TOKEN_HEADER, token);
        return ResponseBuilder.success(token);
    }

    @PostMapping("/find/mail")
    @ApiOperation("找回密码-发送邮件")
    public BaseResponse findAccountSendMail(@RequestBody @Valid AccountFindSendMailRequest request) {
        userService.findAccountSendMail(request.getUsername());
        return ResponseBuilder.success();
    }

    @GetMapping("/find/check/{secret}")
    @ApiOperation("找回密码-验证secret")
    public BaseResponse secretCheck(@PathVariable String secret) {
        userService.checkAccountFindSecret(secret);
        return ResponseBuilder.success();
    }

    @PostMapping("/find/{secret}")
    @ApiOperation("找回密码-重置密码")
    public BaseResponse findAccount(@PathVariable String secret, @RequestBody @Valid AccountFindRequest request) {
        userService.findAccount(secret, request);
        return ResponseBuilder.success();
    }

    @PostMapping("/reset")
    @ApiOperation("重置密码")
    public BaseResponse resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        userService.resetPassword(request);
        return ResponseBuilder.success();
    }

}
