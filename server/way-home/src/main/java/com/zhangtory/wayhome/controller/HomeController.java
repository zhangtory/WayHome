package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:12
 * @Description:
 */
@RestController
public class HomeController {

    @Autowired
    private IHomeService homeService;

    @GetMapping("/get")
    public String getWayHome(HttpServletResponse response) throws IOException {
        response.sendRedirect(homeService.getHomeAddr());
        return "go";
    }

    @PostMapping("/set")
    public String setWayHome(HttpServletRequest request) {
        homeService.setHomeAddr(request);
        return "hello";
    }

}
