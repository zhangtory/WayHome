package com.zhangtory.wayhomecore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangtory.wayhomecore.model.entity.Key;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangtory
 * @since 2019-12-01
 */
public interface KeyMapper extends BaseMapper<Key> {

    /**
     * 通过<username, keyName>的唯一索引查询钥匙信息
     * @return
     */
    Key getByUsernameAndKeyName();

}
