package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IUserService;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResponse addUser(@RequestBody @Valid UserRegisterReq userRegisterReq) {
        userService.register(userRegisterReq);
        return BaseResponseBuilder.success();
    }



}
