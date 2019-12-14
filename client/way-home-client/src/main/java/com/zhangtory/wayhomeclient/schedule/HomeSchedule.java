package com.zhangtory.wayhomeclient.schedule;

import com.alibaba.fastjson.JSONObject;
import com.zhangtory.wayhomeclient.model.HomeInfo;
import com.zhangtory.wayhomeclient.utils.PostUtils;
import com.zhangtory.wayhomeclient.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class HomeSchedule implements ApplicationRunner {

    @Autowired
    private HomeInfo homeInfo;

    @Scheduled(fixedRate = 1000)
    public void sendHomeAddr() {
        log.info("start send home addr...");
        Map<String, Object> params = new HashMap<>();
        params.put("keyId", homeInfo.getKeyId());
        params.put("protocol", homeInfo.getProtocol());
        params.put("port", homeInfo.getPort());
        params.put("path", homeInfo.getPath());
        params.put("timestamp", System.currentTimeMillis());
        params.put("sign", SignUtils.getSign(params, homeInfo.getSecretKey()));
        try {
            String ret = PostUtils.post(homeInfo.getServerUrl(), JSONObject.toJSONString(params));
            log.info(ret);
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
