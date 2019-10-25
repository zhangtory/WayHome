package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.request.UserRegisterReq;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:02
 * @Description:
 */
public interface IUserService {

    /**
     * 用户注册
     */
    void registerUser(UserRegisterReq req);

}
