package com.zhangtory.admin.service;

import com.zhangtory.admin.model.request.AccountFindRequest;
import com.zhangtory.admin.model.request.LoginRequest;
import com.zhangtory.admin.model.request.ResetPasswordRequest;
import com.zhangtory.admin.model.request.UserRegisterRequest;

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

    /**
     * 找回密码-发送邮件
     * @param username
     */
    void findAccountSendMail(String username);

    /**
     * 找回密码-重置密码
     * @param secret
     * @param request
     */
    void findAccount(String secret, AccountFindRequest request);

    /**
     * 验证账户找回秘钥是否可用
     * @param secret
     * @return username
     */
    String checkAccountFindSecret(String secret);

    /**
     * 重置密码
     * @param request
     */
    void resetPassword(ResetPasswordRequest request);

}
