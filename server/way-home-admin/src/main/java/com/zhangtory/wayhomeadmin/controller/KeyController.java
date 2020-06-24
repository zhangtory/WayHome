package com.zhangtory.wayhomeadmin.controller;

import com.zhangtory.wayhomeadmin.component.BaseResponseBuilder;
import com.zhangtory.wayhomeadmin.model.request.ApplyKeyRequest;
import com.zhangtory.wayhomeadmin.model.response.BaseResponse;
import com.zhangtory.wayhomeadmin.model.response.KeyResponse;
import com.zhangtory.wayhomeadmin.service.IKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: zhangtory
 * @date: 6/24 15:36
 * @description: 钥匙相关接口
 */
@RestController
public class KeyController {

    @Autowired
    private IKeyService keyService;

    @GetMapping("/key")
    public BaseResponse queryKeys() {
        List<KeyResponse> keyResponses = keyService.queryUserKeys();
        return BaseResponseBuilder.success(keyResponses);
    }

    @PostMapping("/key")
    public BaseResponse applyKey(@RequestBody @Valid ApplyKeyRequest request) {
        keyService.applyKey(request);
        return BaseResponseBuilder.success();
    }

    @DeleteMapping("/key/{id}")
    public BaseResponse deleteKey(@PathVariable Long id) {
        keyService.deleteKey(id);
        return BaseResponseBuilder.success();
    }

}
