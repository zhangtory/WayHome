package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.request.SetWayHomeReq;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private IHomeService homeService;

    @GetMapping("/go/{appId}")
    public String getWayHome(@PathVariable String appId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(homeService.getWayHome(appId, request));
        return "go";
    }

    @PostMapping("/address/{appID}")
    public BaseResponse setWayHome(@PathVariable String appID, @Valid SetWayHomeReq req, HttpServletRequest request) {
        homeService.setHomeAddr(appID, req, request);
        return BaseResponseBuilder.success();
    }

}
