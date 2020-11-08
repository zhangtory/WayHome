package com.zhangtory.admin;

import com.zhangtory.mybatisplus.component.CodeGenerator;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.CodeGenerationException;

@SpringBootTest
class WayHomeAdminApplicationTests {

    @Autowired
    private CodeGenerator codeGenerator;

    @Test
    void contextLoads() {
        codeGenerator.create("wh_key", "admin", "ZhangTory");
    }

}
