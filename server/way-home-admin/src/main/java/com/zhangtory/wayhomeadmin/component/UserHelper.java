package com.zhangtory.wayhomeadmin.component;

import com.zhangtory.wayhomeadmin.model.entity.User;
import com.zhangtory.wayhomeadmin.model.request.UserRegisterRequest;
import com.zhangtory.wayhomeadmin.utils.PasswordUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author: zhangtory
 * @date: 6/24 9:43
 * @description: 用户工具类
 */
public class UserHelper {

    /**
     * 通过用户注册请求获取用户实体
     * @param request
     * @return
     */
    public static User getRegisterUser(UserRegisterRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setPassword(PasswordUtils.getEncryptedPassword(user.getPassword()));
        return user;
    }

}
