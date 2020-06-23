package com.zhangtory.wayhomecore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangtory.wayhomecore.model.entity.Key;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangtory
 * @since 2019-12-01
 */
@Component
public interface KeyMapper extends BaseMapper<Key> {

    /**
     * 通过<username, keyName>的唯一索引查询钥匙信息
     * @param username
     * @param keyName
     * @return
     */
    @Select("select username,key_name,secret_key,status from wh_key where username=#{username} and key_name=#{keyName}")
    Key getByUsernameAndKeyName(String username, String keyName);

}