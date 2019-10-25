package com.zhangtory.wayhome.dao;

import com.zhangtory.wayhome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:42
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
