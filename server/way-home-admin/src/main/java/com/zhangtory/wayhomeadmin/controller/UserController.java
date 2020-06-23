package com.zhangtory.wayhomeadmin.controller;

import com.zhangtory.wayhomeadmin.component.BaseResponseBuilder;
import com.zhangtory.wayhomeadmin.model.response.BaseResponse;
import com.zhangtory.wayhomeadmin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
