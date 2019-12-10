package com.zhangtory.wayhome;

import com.zhangtory.wayhome.controller.UserController;
import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.mapper.UserMapper;
import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.model.response.BaseResponse;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.List;

@SpringBootTest
class WayHomeApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private IKeyService keyService;


    @Test
    public void registerTest() {
        UserRegisterReq req = new UserRegisterReq();
        req.setUsername("ut1");
        req.setPassword("123456");
        req.setRepassword("123456");
        req.setEmail("1@qq.com");
        req.setMobile("18733334444");
        userService.register(req);
    }

    @Test
    void contextLoads() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
        System.out.println(jws);
    }

}
