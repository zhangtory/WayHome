package com.zhangtory.wayhome.dao;

import com.zhangtory.wayhome.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:42
 * @Description:
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address getByAppId(String appId);

}
