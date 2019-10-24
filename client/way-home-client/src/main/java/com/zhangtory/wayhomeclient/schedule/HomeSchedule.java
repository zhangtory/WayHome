package com.zhangtory.wayhomeclient.schedule;

import com.zhangtory.wayhomeclient.utils.PostUtils;
import com.zhangtory.wayhomeclient.utils.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 12:04
 * @Description:
 */
@Component
public class HomeSchedule implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${protocol}")
    private String protocol;
    @Value("${port}")
    private Long port;
    @Value("${inner_protocol}")
    private String innerProtocol;
    @Value("${inner_ip}")
    private String innerIp;
    @Value("${inner_port}")
    private Long innerPort;
    @Value("${secretKey}")
    private String secretKey;

//    private static final String WAY_HOME_SERVER_URL = "https://wayhome.zhangtory.com/set";
    private static final String WAY_HOME_SERVER_URL = "http://127.0.0.1:8848/set";

    @Scheduled(fixedRate = 10_000)
    public void sendHomeAddr() {
        logger.info("start send home addr...");
        Map<String, Object> params = new HashMap<>();
        params.put("protocol", protocol);
        params.put("port", port);
        params.put("innerProtocol", innerProtocol);
        params.put("innerIp", innerIp);
        params.put("innerPort", innerPort);
        params.put("timestamp", new Date().getTime());
        params.put("sign", SignUtils.getSign(params, secretKey));
        try {
            PostUtils.post(WAY_HOME_SERVER_URL, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            Thread.sleep(1000);
        }
    }
}
