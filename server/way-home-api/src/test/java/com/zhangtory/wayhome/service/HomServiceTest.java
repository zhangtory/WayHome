package com.zhangtory.wayhome.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ZhangYaoYu
 * @date 2019/12/13 22:55
 */
@SpringBootTest
public class HomServiceTest {

    @Autowired
    private IHomeService homeService;

    @Test
    public void addressTest() {
        String addr = homeService.getAddress("e51dbe4299cb46cfab885cdb3e1b7df5");
        System.out.println(addr);
    }

}
