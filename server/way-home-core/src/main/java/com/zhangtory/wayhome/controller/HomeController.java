package com.zhangtory.wayhome.controller;

import com.zhangtory.core.response.BaseResponse;
import com.zhangtory.core.response.ResponseBuilder;
import com.zhangtory.core.util.IpUtils;
import com.zhangtory.wayhome.model.request.SetAddressRequest;
import com.zhangtory.wayhome.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author zhangtory
 * @date 2020/6/22 21:51
 * @description: 地址相关接口
 */
@RestController
public class HomeController {

    @Autowired
    private IHomeService homeService;

    @GetMapping("/address/{username}/{keyName}")
    public BaseResponse getAddress(@PathVariable String username, @PathVariable String keyName) {
        return ResponseBuilder.success(homeService.getAddress(username, keyName));
    }

    @PostMapping("/address")
    public BaseResponse setAddress(HttpServletRequest request, @RequestBody @Valid SetAddressRequest addr) {
        homeService.setAddress(IpUtils.getIpAddr(request), addr);
        return ResponseBuilder.success();
    }

}
