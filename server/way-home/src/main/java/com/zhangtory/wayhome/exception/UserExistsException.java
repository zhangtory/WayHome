package com.zhangtory.wayhome.exception;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 11:24
 * @Description:
 */
public class UserExistsException extends RuntimeException {

    public UserExistsException() {
    }

    public UserExistsException(String message) {
        super(message);
    }

}
