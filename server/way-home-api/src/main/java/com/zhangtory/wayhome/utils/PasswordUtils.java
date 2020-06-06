package com.zhangtory.wayhome.utils;

import com.zhangtory.wayhome.constant.ConfigConstant;

import java.util.Random;

/**
 * @author ZhangYaoYu
 * @date 2019/12/7 20:17
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
        while (salt.toString().length() < ConfigConstant.PASSWORD_SALT_LEN) {
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
