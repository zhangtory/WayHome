package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangtory.wayhome.model.request.UserRegisterReq;

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
     * @param userRegisterReq
     */
    void register(UserRegisterReq userRegisterReq);

}
