package com.zhangtory.wayhomecore.controller;

import com.zhangtory.wayhomecore.component.BaseResponseBuilder;
import com.zhangtory.wayhomecore.model.request.SetAddressRequest;
import com.zhangtory.wayhomecore.model.response.BaseResponse;
import com.zhangtory.wayhomecore.service.IHomeService;
import com.zhangtory.wayhomecore.utils.IpUtils;
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
        return BaseResponseBuilder.success(homeService.getAddress(username.toLowerCase(), keyName));
    }

    @PostMapping("/address")
    public BaseResponse setAddress(HttpServletRequest request, @RequestBody @Valid SetAddressRequest addr) {
        homeService.setAddress(IpUtils.getIpAddr(request), addr);
        return BaseResponseBuilder.success();
    }

}
