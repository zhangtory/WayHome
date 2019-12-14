package com.zhangtory.wayhome.utils;

import com.zhangtory.wayhome.constant.CodeConstant;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 10:44
 * @Description:
 */
public class SignUtils {

    /**
     * 检查时间戳是否在指定时间范围内，防止重放攻击
     * @param timestamp
     * @return
     */
    public static boolean checkTimestamp(Long timestamp) {
        if (Math.abs(timestamp - System.currentTimeMillis()) < CodeConstant.TIMESTAMP_RANGE) {
            return true;
        }
        return false;
    }

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
            if (value == null) {
                continue;
            }
            String trim = String.valueOf(value).trim();
            if ("".equals(trim)) {
                continue;
            }
            sb.append(key).append("=").append(trim).append("&");
        }
        sb.append("secretKey=").append(secretKey);
        String sign = EncryptUtils.md5(sb.toString()).toUpperCase();
        return sign;
    }

}
