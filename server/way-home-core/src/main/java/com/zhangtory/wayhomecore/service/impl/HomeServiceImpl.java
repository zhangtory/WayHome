package com.zhangtory.wayhomecore.service.impl;

import com.zhangtory.wayhomecore.mapper.KeyMapper;
import com.zhangtory.wayhomecore.model.entity.Key;
import com.zhangtory.wayhomecore.service.IHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangtory
 * @date 2020/6/22 21:53
 * @description: 地址相关业务
 */
@Service
@Slf4j
public class HomeServiceImpl implements IHomeService {
    
    @Autowired
    private KeyMapper keyMapper;
    
    @Override
    public Key getById(long id) {
        return keyMapper.selectById(id);
    }
}
