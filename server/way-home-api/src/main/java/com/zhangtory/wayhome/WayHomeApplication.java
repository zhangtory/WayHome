package com.zhangtory.wayhome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangYaoYu
 * WayHome - 代替DDNS更好的选择
 */
@SpringBootApplication
@MapperScan("com.zhangtory.wayhome.mapper")
public class WayHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayHomeApplication.class, args);
    }

}
