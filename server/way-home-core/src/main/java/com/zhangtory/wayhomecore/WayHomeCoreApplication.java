package com.zhangtory.wayhomecore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author zhangtory
 * WayHome - 代替DDNS更好的选择
 * https://wayhome.zhangtory.com/
 */
@SpringBootApplication
@MapperScan("com.zhangtory.wayhomecore.mapper")
public class WayHomeCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayHomeCoreApplication.class, args);
    }

}
