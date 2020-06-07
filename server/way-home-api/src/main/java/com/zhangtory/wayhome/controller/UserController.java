package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.constant.CodeConstant;
import com.zhangtory.wayhome.model.request.LoginRequest;
import com.zhangtory.wayhome.model.request.ResetPasswordRequest;
import com.zhangtory.wayhome.model.request.UserRegisterRequest;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IUserService;
import com.zhangtory.wayhome.model.response.BaseResponseBuilder;
import com.zhangtory.wayhome.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author ZhangYaoYu
 * @date 2019/12/7 20:35
 */
@RestController
@Api(value = "用户相关接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public BaseResponse register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
        return BaseResponseBuilder.success();
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public BaseResponse login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
        String token = userService.login(loginRequest);
        response.setHeader(CodeConstant.TOKEN_HEADER, token);
        return BaseResponseBuilder.success(CodeConstant.SUCCESS_MSG, token);
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "修改密码")
    public BaseResponse changePassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
        return BaseResponseBuilder.success();
    }

    @PostMapping("/check")
    @ApiOperation(value = "检查用户登录情况")
    public BaseResponse check(HttpServletRequest request) {
        String token = request.getHeader(CodeConstant.TOKEN_HEADER);
        String username = JwtUtils.getSubject(token);
        return BaseResponseBuilder.success(CodeConstant.SUCCESS_MSG, username);
    }

}
