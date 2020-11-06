package com.zhangtory.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangtory.admin.model.entity.WhUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangtory
 * @date 2020/6/23 20:37
 * @description: 用户信息
 */
@Mapper
public interface WhUserMapper extends BaseMapper<WhUser> {
}
