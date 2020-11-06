package com.zhangtory.wayhome.config;

import com.zhangtory.core.util.IpUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 17:13
 * @Description: 请求方ip地址回显
 */
@WebFilter(filterName = "ipEchoFilter", urlPatterns = {"/ip"})
public class IpEchoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ipAddr = IpUtils.getIpAddr((HttpServletRequest) servletRequest);
        servletResponse.getWriter().print(ipAddr);
    }

}
