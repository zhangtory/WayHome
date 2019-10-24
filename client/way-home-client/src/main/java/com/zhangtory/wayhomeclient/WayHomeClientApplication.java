package com.zhangtory.wayhomeclient;

import com.zhangtory.wayhomeclient.model.HomeInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({HomeInfo.class})
public class WayHomeClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayHomeClientApplication.class, args);
    }

}
