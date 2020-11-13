package com.zhangtory.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 11:38
 * @Description:
 */
@SpringBootTest
public class MainTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin@zhangtory.com");
        message.setTo("zhangtory@qq.com");
        message.setSubject("[WayHome]用户密码找回");
        message.setText("请点击链接重置密码：");
        mailSender.send(message);
    }

}
