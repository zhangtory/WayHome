package com.zhangtory.wayhomeadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangtory
 * WayHome - 代替DDNS更好的选择
 * https://wayhome.zhangtory.com/
 */
@SpringBootApplication
@MapperScan("com.zhangtory.wayhomeadmin.mapper")
public class WayHomeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayHomeAdminApplication.class, args);
    }

}
