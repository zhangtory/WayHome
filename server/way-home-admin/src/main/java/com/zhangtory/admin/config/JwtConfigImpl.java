package com.zhangtory.admin.config;

import com.zhangtory.jwt.config.JwtConfig;
import org.springframework.stereotype.Component;

/**
 * @author zhangtory
 * @date 2020/11/8 16:15
 * @description: jwt设置
 */
@Component("jwtConfig")
public class JwtConfigImpl extends JwtConfig {
    @Override
    public void initJwtConfig() {
        this.patterns.add("/index/**");
        this.patterns.add("/user/**");
    }
}
