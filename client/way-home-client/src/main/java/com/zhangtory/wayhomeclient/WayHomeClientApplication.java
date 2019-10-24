package com.zhangtory.wayhomeclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WayHomeClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayHomeClientApplication.class, args);
    }

}
