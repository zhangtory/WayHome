package com.zhangtory.wayhomeadmin.controller;

import com.zhangtory.wayhomeadmin.component.BaseResponseBuilder;
import com.zhangtory.wayhomeadmin.constant.BaseConstant;
import com.zhangtory.wayhomeadmin.model.request.LoginRequest;
import com.zhangtory.wayhomeadmin.model.request.UserRegisterRequest;
import com.zhangtory.wayhomeadmin.model.response.BaseResponse;
import com.zhangtory.wayhomeadmin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author zhangtory
 * @date 2020/6/23 20:35
 * @description: 用户相关接口
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/index")
    public BaseResponse index() {
        return BaseResponseBuilder.success("test");
    }

    @PostMapping("/register")
    public BaseResponse register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
        return BaseResponseBuilder.success();
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
        String token = userService.login(loginRequest);
        response.setHeader(BaseConstant.TOKEN_HEADER, token);
        return BaseResponseBuilder.success(token);
    }

}
