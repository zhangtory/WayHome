package com.zhangtory.wayhomecore.service;

import com.zhangtory.wayhomecore.model.entity.Key;

/**
 * @author zhangtory
 * @date 2020/6/22 21:53
 * @description: 地址相关业务
 */
public interface IHomeService {
    
    Key getById(long id);
    
}
