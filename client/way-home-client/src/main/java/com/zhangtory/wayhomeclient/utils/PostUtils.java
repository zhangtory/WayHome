package com.zhangtory.wayhomeclient.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 12:03
 * @Description:
 */
public class PostUtils {

    private static final String ENCODING = "UTF-8";

    /**
     * 设置连接超时时间，单位毫秒
     */
    private static final int CONNECT_TIMEOUT = 10_000;

    /**
     * 请求获取数据的超时时间(即响应时间)，单位毫秒
     */
    private static final int SOCKET_TIMEOUT = 10_000;

    public static String post(String url, Map<String, String> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        // config
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
        httpPost.setConfig(requestConfig);
        // set headers
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.setHeader("User-Agent", "way-home-client 1.2");
        // build params
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        params.forEach((k, v) -> {
            NameValuePair nv = new BasicNameValuePair(k, v);
            nameValuePairs.add(nv);
        });
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, ENCODING));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String str = EntityUtils.toString(entity, ENCODING);
        response.close();
        return str;
    }

}
