package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.request.ChangePasswordReq;
import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.model.response.DashboardResp;

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

    /**
     * 修改密码
     *
     * @param req
     */
    void changePassword(ChangePasswordReq req, String username);

    /**
     * 控制台信息
     *
     * @param username
     * @return
     */
    DashboardResp getDashboardInfo(String username);

}
