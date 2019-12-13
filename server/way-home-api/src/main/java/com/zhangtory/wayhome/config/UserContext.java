package com.zhangtory.wayhome.config;

import com.zhangtory.wayhome.constant.CodeConstant;
import com.zhangtory.wayhome.utils.JwtUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangYaoYu
 * @date 2019/12/13 21:04
 */
public class UserContext {

    public static String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(CodeConstant.TOKEN_HEADER);
        return token;
    }

    public static Long getUserId() {
        return Long.parseLong(JwtUtils.getId(getToken()));
    }

    public static String getUsername() {
        return JwtUtils.getSubject(getToken());
    }

}
