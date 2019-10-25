package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 16:07
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public void login() {
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterReq req) {
        userService.registerUser(req);
        return "success";
    }

    @PostMapping("/password")
    public String changePassword() {

        return "";
    }

}
