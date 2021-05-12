package com.zhangtory.admin.componet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: ZhangYaoYu
 * @Date: 2020/12/31 14:56
 * @Description:
 */
@Component
public class ShortUrlCreator {

    /**
     * 短链平台接口地址
     */
    private static final String API_URL = "http://api.3w.cn/api.htm?domain={domain}&expireDate={expireDate}&key={key}&url={url}";

    @Value("${short_url_api_token}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 短链平台：https://3w.cn/api.html
     * @param longUrl
     * @return
     */
    public String getShortUrl(String longUrl) {
        Map<String, String> params = new HashMap<>();
        params.put("domain", "0");
        params.put("expireDate", "2040-01-01");
        params.put("key", key);
        params.put("url", longUrl);
        return restTemplate.exchange(API_URL, HttpMethod.GET, null, String.class, params).getBody();
    }

}
