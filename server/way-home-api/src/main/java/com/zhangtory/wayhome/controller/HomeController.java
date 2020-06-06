package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.request.SetWayHomeRequest;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IHomeService;
import com.zhangtory.wayhome.model.response.BaseResponseBuilder;
import com.zhangtory.wayhome.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author ZhangYaoYu
 * @date 2019/12/12 21:56
 */
@RestController
public class HomeController {

    @Autowired
    private IHomeService homeService;

    @PostMapping("/address")
    public BaseResponse setAddress(HttpServletRequest request, @RequestBody @Valid SetWayHomeRequest req) {
        homeService.setAddress(req, IpUtils.getIpAddr(request));
        return BaseResponseBuilder.success();
    }

    @GetMapping("/go/{keyId}")
    public void setAddress(@PathVariable String keyId, HttpServletResponse response) throws IOException {
        String address = homeService.getAddress(keyId);
        response.sendRedirect(address);
    }

}
