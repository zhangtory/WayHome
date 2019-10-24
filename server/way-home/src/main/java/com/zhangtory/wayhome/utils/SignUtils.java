package com.zhangtory.wayhome.utils;

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

    public static boolean checkSign(Map<String, Object> params, String secretKey, String signStr) {
        params.remove("sign");
        if (signStr.equals(getSign(params, secretKey))) {
            return true;
        }
        return false;
    }

    public static String getSign(Map<String, Object> params, String secretKey) {
        //准备签名字符串
        StringBuilder sb = new StringBuilder();
        Map<String, Object> map = new TreeMap(params);
        // 遍历排序的字典,并拼接"key=value&key=value"格式，空value不参与签名
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (null == value) {
                continue;
            }
            String s = String.valueOf(value);
            String trim = s.trim();
            if (trim.equals("")) {
                continue;
            }
            sb.append(key).append("=").append(trim).append("&");
        }
        sb.append("secretKey=").append(secretKey);
        String sign = md5(sb.toString()).toUpperCase();
        return sign;
    }

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
