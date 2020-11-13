package com.zhangtory.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhangtory.admin.model.request.AddKeyRequest;
import com.zhangtory.admin.model.vo.KeyInfoVO;
import com.zhangtory.admin.service.IKeyService;
import com.zhangtory.core.response.BaseResponse;
import com.zhangtory.core.response.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 17:29
 * @Description: 钥匙相关接口
 */
@RestController
@RequestMapping("/key")
@Api(tags = "钥匙相关接口")
public class KeyController {

    @Autowired
    private IKeyService keyService;

    @GetMapping("/query/{current}")
    @ApiOperation("查询所有的key")
    public BaseResponse query(@PathVariable Long current) {
        IPage<KeyInfoVO> keys = keyService.queryKeys(current);
        return ResponseBuilder.success(keys);
    }

    @PostMapping("/")
    @ApiOperation("添加key")
    public BaseResponse addKey(@RequestBody @Valid AddKeyRequest request) {
        keyService.addKey(request);
        return ResponseBuilder.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除指定key")
    public BaseResponse deleteKey(@PathVariable Long id) {
        keyService.deleteKey(id);
        return ResponseBuilder.success();
    }

}
