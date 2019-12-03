package com.zhangtory.wayhome;

import com.zhangtory.wayhome.entity.Key;
import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.mapper.UserMapper;
import com.zhangtory.wayhome.service.IKeyService;
import com.zhangtory.wayhome.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WayHomeApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private IKeyService keyService;


    @Test
    void userServiceTest() {
        User user = new User();
        user.setUsername("name3");
        user.setPassword("123");
        user.setEmail("3@q.com");
        user.setMobile("1922321");
        userService.save(user);
    }

    @Test
    void queryUserTest() {
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    void addKeyTest() {
        Key key = new Key();
        key.setKeyId("44556");
        key.setSecretKey("kjjgbk");
        key.setUserId(41L);
        keyService.save(key);
    }

    @Test
    void contextLoads() {
    }

}
