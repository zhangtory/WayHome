package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.request.ChangePasswordReq;
import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.model.response.DashboardResp;
import com.zhangtory.wayhome.service.IUserService;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public BaseResponse register(@Valid @ModelAttribute UserRegisterReq req) {
        userService.registerUser(req);
        return BaseResponseBuilder.success();
    }

    @PostMapping("/password")
    public BaseResponse changePassword(@Valid @ModelAttribute ChangePasswordReq req, Authentication authentication) {
        userService.changePassword(req, authentication.getName());
        return BaseResponseBuilder.success();
    }

    @PostMapping("/dashboard")
    public BaseResponse dashboard(Authentication authentication) {
        DashboardResp dashboardResp = userService.getDashboardInfo(authentication.getName());
        return BaseResponseBuilder.success(dashboardResp);
    }

}
