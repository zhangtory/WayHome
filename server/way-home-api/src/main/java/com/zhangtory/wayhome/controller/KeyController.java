package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.entity.Key;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.model.response.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Key key = keyService.addKey();
        return BaseResponseBuilder.success(key);
    }

    @GetMapping("/key")
    public BaseResponse queryKeys() {
        List<Key> keyList = keyService.queryKeys();
        return BaseResponseBuilder.success(keyList);
    }

    @DeleteMapping("/key/{id}")
    public BaseResponse queryKeys(@PathVariable String id) {
        keyService.deleteKey(id);
        return BaseResponseBuilder.success();
    }

}
