package com.zhangtory.wayhome.exception;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 11:24
 * @Description:
 */
public class PasswordErrorException extends RuntimeException {

    public PasswordErrorException() {}

    public PasswordErrorException(String message) {
        super(message);
    }

}
