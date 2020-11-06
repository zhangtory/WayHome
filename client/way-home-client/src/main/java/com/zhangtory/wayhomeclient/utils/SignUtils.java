package com.zhangtory.wayhomeclient.utils;

import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 10:44
 * @Description:
 */
public class SignUtils {

    /**
     * 计算签名
     * @param params
     * @param secretKey
     * @return
     */
    public static String getSign(Map<String, String> params, String secretKey) {
        Map<String, String> sortMap = new TreeMap(params);
        // 遍历排序的字典,并拼接"key + value + key + value + secret"格式，空value不参与签名
        StringBuffer originStr = new StringBuffer();
        sortMap.forEach((k, v) -> {
            if (!StringUtils.isEmpty(v)) {
                originStr.append(k).append(v);
            }
        });
        originStr.append(secretKey);
        String sign = md5(originStr.toString()).toUpperCase();
        return sign;
    }

    /**
     * 计算md5摘要
     * @param origin
     * @return
     */
    private static String md5(String origin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(origin.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
