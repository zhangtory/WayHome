package com.zhangtory.admin.controller;

import com.zhangtory.core.response.BaseResponse;
import com.zhangtory.core.response.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 17:23
 * @Description: 首页
 */
@RestController
@RequestMapping("/index")
@Api(tags = "首页")
public class IndexController {

    @GetMapping("/")
    @ApiOperation("主页")
    public BaseResponse index() {
        return ResponseBuilder.success("test");
    }

}
