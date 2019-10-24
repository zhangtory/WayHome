package com.zhangtory.wayhomeclient.schedule;

import com.zhangtory.wayhomeclient.model.HomeInfo;
import com.zhangtory.wayhomeclient.utils.PostUtils;
import com.zhangtory.wayhomeclient.utils.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HomeInfo homeInfo;

    private static final String WAY_HOME_SERVER_URL = "https://wayhome.zhangtory.com/set";

    @Scheduled(fixedRate = 1_000)
    public void sendHomeAddr() {
        logger.info("start send home addr...");
        Map<String, Object> params = new HashMap<>();
        params.put("protocol", homeInfo.getProtocol());
        params.put("port", homeInfo.getPort());
        params.put("innerProtocol", homeInfo.getInnerProtocol());
        params.put("innerIp", homeInfo.getInnerIpAddr());
        params.put("innerPort", homeInfo.getInnerPort());
        params.put("timestamp", new Date().getTime());
        params.put("sign", SignUtils.getSign(params, homeInfo.getSecretKey()));
        try {
            String ret = PostUtils.post(WAY_HOME_SERVER_URL, params);
            logger.info(ret);
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
