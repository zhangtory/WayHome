package com.zhangtory.wayhomeadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zhangtory
 * @date 2019/12/12 21:02
 * @description: 拦截器注册
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * 用户登录检查
         * 除了注册/登录/跳转接口，其他接口必须要登录
         */
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register");
    }
}
