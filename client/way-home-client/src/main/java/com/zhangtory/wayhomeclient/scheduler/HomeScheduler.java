package com.zhangtory.wayhomeclient.scheduler;

import com.zhangtory.wayhomeclient.model.HomeInfo;
import com.zhangtory.wayhomeclient.utils.PostUtils;
import com.zhangtory.wayhomeclient.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 12:04
 * @Description:
 */
@Component
@Slf4j
public class HomeScheduler implements ApplicationRunner {

    @Autowired
    private HomeInfo homeInfo;

    private void sendHomeAddr() throws IOException {
        log.info("start send home addr...");
        Map<String, String> params = new HashMap<>(16);
        params.put("username", homeInfo.getUsername());
        params.put("keyName", homeInfo.getKeyName());
        params.put("protocol", homeInfo.getProtocol());
        params.put("port", homeInfo.getPort().toString());
        params.put("path", homeInfo.getPath());
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("sign", SignUtils.getSign(params, homeInfo.getSecretKey()));
        String ret = PostUtils.post(homeInfo.getServerUrl(), params);
        log.info(ret);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            try {
                sendHomeAddr();
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }
}
