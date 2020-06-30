package com.zhangtory.wayhomeadmin.service;

import com.zhangtory.wayhomeadmin.model.request.LoginRequest;
import com.zhangtory.wayhomeadmin.model.request.UserRegisterRequest;

/**
 * @author zhangtory
 * @date 2020/6/23 20:45
 * @description: 用户相关接口
 */
public interface IUserService {

    /**
     * 用户注册
     * @param request
     * @return token
     */
    String register(UserRegisterRequest request);

    /**
     * 用户登录
     * @param request
     * @return token
     */
    String login(LoginRequest request);

}
