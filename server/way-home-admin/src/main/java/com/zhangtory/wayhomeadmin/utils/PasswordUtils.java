package com.zhangtory.wayhomeadmin.utils;

import com.zhangtory.wayhomeadmin.constant.BaseConstant;

import java.util.Random;

/**
 * @author zhangtory
 * @date 2019/12/7 20:17
 * @description: 用户密码工具
 */
public class PasswordUtils {

    /**
     * 密码加密
     * @param password 明文密码
     * @return
     */
    public static String getEncryptedPassword(String password) {
        Random random = new Random();
        StringBuilder salt = new StringBuilder(String.valueOf(random.nextInt(999999)));
        while (salt.toString().length() < BaseConstant.PASSWORD_SALT_LEN) {
            salt.append("0");
        }
        String encode = EncryptUtils.md5(password + salt.toString());
        return encode + salt.toString();
    }

    /**
     * 验证密码是否正确
     * @param password 明文密码
     * @param encryptedPassword 加密密码字符串
     * @return
     */
    public static boolean checkPassword(String password, String encryptedPassword) {
        String salt = encryptedPassword.substring(32);
        String encode = EncryptUtils.md5(password + salt) + salt;
        if (encode.equals(encryptedPassword)) {
            return true;
        }
        return false;
    }

}
