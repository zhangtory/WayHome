package com.zhangtory.wayhome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhangtory.wayhome.mapper")
public class WayHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayHomeApplication.class, args);
    }

}
