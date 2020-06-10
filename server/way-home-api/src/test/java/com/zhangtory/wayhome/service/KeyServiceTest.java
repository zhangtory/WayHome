package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.entity.Key;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author ZhangYaoYu
 * @date 2019/12/13 21:15
 */
@SpringBootTest
public class KeyServiceTest {

    @Autowired
    private IKeyService keyService;

    @Test
    public void addKeyTest() {
        Key key = keyService.addKey();
        System.out.println(key);
    }

    @Test
    public void queryKeysTest() {
        List<Key> keyList = keyService.queryKeys();
        keyList.forEach(System.out::println);
    }

    @Test
    public void deleteKey() {
        String keyId = "";
        keyService.deleteKey(keyId);
        Key key = keyService.getById(keyId);
        System.out.println(key);
    }

}
