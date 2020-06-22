package com.zhangtory.wayhomecore.controller;

import com.zhangtory.wayhomecore.config.BaseResponseBuilder;
import com.zhangtory.wayhomecore.model.response.BaseResponse;
import com.zhangtory.wayhomecore.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author zhangtory
 * @date 2020/6/22 21:51
 * @description: 地址相关接口
 */
@RestController
public class HomeController {

    @Autowired
    private IHomeService homeService;

    @GetMapping("/index/{id}")
    public BaseResponse index(@PathVariable long id) {
        return BaseResponseBuilder.success(homeService.getById(id));
    }

}
