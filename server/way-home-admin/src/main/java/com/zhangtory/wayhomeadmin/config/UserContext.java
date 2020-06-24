package com.zhangtory.wayhomeadmin.config;

import com.zhangtory.wayhomeadmin.constant.BaseConstant;
import com.zhangtory.wayhomeadmin.utils.JwtUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangtory
 * @date 2019/12/13 21:04
 * @description: 用户上下文，用于获取当前登录用户信息
 */
public class UserContext {

    public static String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(BaseConstant.TOKEN_HEADER);
        return token;
    }

    public static Long getUserId() {
        return Long.parseLong(JwtUtils.getId(getToken()));
    }

    public static String getUsername() {
        return JwtUtils.getSubject(getToken());
    }

}
