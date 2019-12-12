package com.zhangtory.wayhome.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhangtory.wayhome.constant.CodeConstant;
import com.zhangtory.wayhome.constant.ExceptionConstant;
import com.zhangtory.wayhome.exception.UserException;
import com.zhangtory.wayhome.utils.JwtUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhangYaoYu
 * @date 2019/12/12 21:00
 */
@Configuration
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查是否登录
        String token = request.getHeader(CodeConstant.TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            throw new UserException(ExceptionConstant.USER_NOT_LOGIN);
        }
        // 检查token是否正确
        JwtUtils.getTokenBody(token);
        return true;
    }

}
