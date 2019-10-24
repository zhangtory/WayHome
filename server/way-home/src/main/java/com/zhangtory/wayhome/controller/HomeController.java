package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.SetWayHomeReq;
import com.zhangtory.wayhome.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:12
 * @Description:
 */
@RestController
public class HomeController {

    @Autowired
    private IHomeService homeService;

    @GetMapping("/go")
    public String getWayHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(homeService.getWayHome(request));
        return "go";
    }

    @GetMapping("/get")
    public String getWayHomeAddr(HttpServletRequest request) {
        return homeService.getWayHome(request);
    }

    @PostMapping("/set")
    public String setWayHome(@Valid SetWayHomeReq req, HttpServletRequest request) {
        homeService.setHomeAddr(req, request);
        return "hello";
    }

}
