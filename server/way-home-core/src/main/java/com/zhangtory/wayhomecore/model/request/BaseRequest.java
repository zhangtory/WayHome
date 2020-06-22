package com.zhangtory.wayhomecore.model.request;

import lombok.Data;

/**
 * @author zhangtory
 * @date 2020/6/22 22:29
 * @description: 统一请求类
 */
@Data
public class BaseRequest {
    
    private String sign;
    
    private long timestamp;
    
}
