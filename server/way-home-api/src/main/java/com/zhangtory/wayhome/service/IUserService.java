package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangtory.wayhome.model.request.LoginRequest;
import com.zhangtory.wayhome.model.request.ResetPasswordRequest;
import com.zhangtory.wayhome.model.request.UserRegisterRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangtory
 * @since 2019-12-01
 */
public interface IUserService extends IService<User> {

    /**
     * 用户注册
     * @param userRegisterRequest
     */
    void register(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    String login(LoginRequest loginRequest);

    /**
     * 修改密码
     * @param resetPasswordRequest
     */
    void resetPassword(ResetPasswordRequest resetPasswordRequest);

}
