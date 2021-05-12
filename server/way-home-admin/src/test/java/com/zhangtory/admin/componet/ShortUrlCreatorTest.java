package com.zhangtory.admin.componet;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: ZhangYaoYu
 * @Date: 2020/12/31 15:21
 * @Description:
 */
@SpringBootTest
@Slf4j
class ShortUrlCreatorTest {

    @Autowired
    private ShortUrlCreator shortUrlCreator;

    @Test
    public void shortUrlTest() {
        String url = "https://wayhome.zhangtory.com";
        String shortUrl = shortUrlCreator.getShortUrl(url);
        log.info(shortUrl);
    }

}