package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IUserService;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 16:07
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public BaseResponse register(@ModelAttribute UserRegisterReq req) {
        userService.registerUser(req);
        return BaseResponseBuilder.success();
    }

    @PostMapping("/password")
    public BaseResponse changePassword() {

        return BaseResponseBuilder.success();
    }

    @PostMapping("/dashboard")
    public BaseResponse dashboard() {

        return BaseResponseBuilder.success();
    }

}
