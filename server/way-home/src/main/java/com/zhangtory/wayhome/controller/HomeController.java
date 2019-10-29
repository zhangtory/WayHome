package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.request.SetWayHomeReq;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/go/{appID}")
    public String getWayHome(@PathVariable String appID, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(homeService.getWayHome(appID, request));
        return "go";
    }

    @GetMapping("/address/{appID}")
    public BaseResponse getWayHomeAddr(@PathVariable String appID, HttpServletRequest request) {
        return BaseResponseBuilder.success(homeService.getWayHome(appID, request));
    }

    @PostMapping("/address/{appID}")
    public BaseResponse setWayHome(@PathVariable String appID, @Valid SetWayHomeReq req, HttpServletRequest request) {
        homeService.setHomeAddr(appID, req, request);
        return BaseResponseBuilder.success();
    }

}
