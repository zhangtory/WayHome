package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.utils.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangYaoYu
 * @date 2019/12/7 20:35
 */
@RestController
public class KeyController {

    @Autowired
    private IKeyService keyService;

    @PostMapping("/key")
    public BaseResponse addKey() {

        return BaseResponseBuilder.success();
    }

    @GetMapping("/key")
    public BaseResponse queryKeys() {

        return BaseResponseBuilder.success();
    }

    @DeleteMapping("/key/{id}")
    public BaseResponse queryKeys(@PathVariable String id) {

        return BaseResponseBuilder.success(id);
    }

}
