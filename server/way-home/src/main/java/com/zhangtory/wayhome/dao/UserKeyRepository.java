package com.zhangtory.wayhome.dao;

import com.zhangtory.wayhome.entity.UserKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:42
 * @Description:
 */
public interface UserKeyRepository extends JpaRepository<UserKey, Long> {

    UserKey getByAppId(String appId);

    List<UserKey> findAllByUserId(Long userId);

    Long countByUserId(Long userId);

    UserKey getByUserIdAndAppId(Long userId, String appId);

}
