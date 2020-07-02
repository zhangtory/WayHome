package com.zhangtory.wayhomeadmin.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhangtory
 * @date 2019/12/7 20:35
 * @description: 加密工具
 */
public class EncryptUtils {

    /**
     * 对字符串进行MD5摘要
     * @param origin
     * @return
     */
    public static String md5(String origin) {
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
