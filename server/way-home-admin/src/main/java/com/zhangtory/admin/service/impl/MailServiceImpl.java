package com.zhangtory.admin.service.impl;

import com.zhangtory.admin.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 13:36
 * @Description:
 */
@Service
@Slf4j
public class MailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailAuthUsername;

    /**
     * 给用户发送邮件
     *
     * @param email
     * @param title
     * @param text
     */
    @Override
    public void sendMail(String email, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailAuthUsername);
        message.setTo(email);
        message.setSubject(title);
        message.setText(text);
        mailSender.send(message);
    }
}
