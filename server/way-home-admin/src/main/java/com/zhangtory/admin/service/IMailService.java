package com.zhangtory.admin.service;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 13:35
 * @Description:
 */
public interface IMailService {

    /**
     * 给用户发送邮件
     * @param email
     * @param title
     * @param text
     */
    void sendMail(String email, String title, String text);

}
