package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.model.response.UserKeyResp;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 16:13
 * @Description:
 */
@RestController
public class KeyController {

    @Autowired
    private IKeyService keyService;

    @PostMapping("/key")
    public BaseResponse apply(Authentication authentication) {
        UserKeyResp userKeyResp = keyService.applyUserKey(authentication.getName());
        return BaseResponseBuilder.success(userKeyResp);
    }

    @DeleteMapping("/key/{appId}")
    public BaseResponse delete(Authentication authentication, @PathVariable String appId) {
        keyService.deleteUserKey(authentication.getName(), appId);
        return BaseResponseBuilder.success();
    }

    @GetMapping("/key")
    public BaseResponse query(Authentication authentication) {
        List<UserKeyResp> list = keyService.queryAllUserKey(authentication.getName());
        return BaseResponseBuilder.success(list);
    }

}
