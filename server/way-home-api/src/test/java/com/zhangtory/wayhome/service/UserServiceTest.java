package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.request.LoginReq;
import com.zhangtory.wayhome.model.request.ResetPasswordReq;
import com.zhangtory.wayhome.model.request.UserRegisterReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ZhangYaoYu
 * @date 2019/12/13 21:33
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void registerTest() {
        UserRegisterReq req = new UserRegisterReq();
        req.setUsername("ut123");
        req.setPassword("123456");
        req.setRepassword("123456");
        req.setEmail("1@qq.com");
        req.setMobile("18733334444");
        userService.register(req);
    }

    @Test
    public void loginTest() {
        System.out.println(login("ut123", "123456"));
    }

    @Test
    public void resetPasswordTest() {
        ResetPasswordReq req = new ResetPasswordReq();
        req.setOldPassword("123456");
        req.setNewPassword("111111");
        req.setReNewPassword("111111");
        userService.resetPassword(req);
        System.out.println(login("ut123", "123456"));
        System.out.println(login("ut123", "111111"));
    }

    private String login(String username, String password) {
        LoginReq req = new LoginReq();
        req.setUsername(username);
        req.setPassword(password);
        String token = userService.login(req);
        return token;
    }

}
