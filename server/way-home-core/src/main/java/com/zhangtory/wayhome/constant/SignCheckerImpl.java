package com.zhangtory.wayhome.constant;

import com.zhangtory.sign.SignChecker;
import com.zhangtory.wayhome.model.vo.KeyAddressVO;
import com.zhangtory.wayhome.service.IKeyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 15:09
 * @Description: 自定义验签功能
 */
@Component("signChecker")
public class SignCheckerImpl extends SignChecker {

    @Autowired
    private IKeyAddressService keyAddressService;

    @Override
    public void initCheckInterceptor() {
        this.patterns.add("/get/**");
    }

    @Override
    public String getSecret() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String username = request.getParameter("username");
        String keyName = request.getParameter("keyName");
        KeyAddressVO keyAddress = keyAddressService.getKeyAddress(username, keyName);
        return keyAddress.getSecretKey();
    }

}
