package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.model.entity.Key;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.model.response.BaseResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZhangYaoYu
 * @date 2019/12/7 20:35
 */
@RestController
@Api(value = "Key相关接口")
public class KeyController {

    @Autowired
    private IKeyService keyService;

    @PostMapping("/key")
    @ApiOperation(value = "申请key")
    public BaseResponse addKey() {
        Key key = keyService.addKey();
        return BaseResponseBuilder.success(key);
    }

    @GetMapping("/key")
    @ApiOperation(value = "查询当前用户的key列表")
    public BaseResponse queryKeys() {
        List<Key> keyList = keyService.queryKeys();
        return BaseResponseBuilder.success(keyList);
    }

    @DeleteMapping("/key/{id}")
    @ApiOperation(value = "删除指定key")
    public BaseResponse deleteKeys(@PathVariable String id) {
        keyService.deleteKey(id);
        return BaseResponseBuilder.success();
    }

}
