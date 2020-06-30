package com.zhangtory.wayhomeadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangtory.wayhomeadmin.component.UserHelper;
import com.zhangtory.wayhomeadmin.constant.ResultCode;
import com.zhangtory.wayhomeadmin.exception.UserException;
import com.zhangtory.wayhomeadmin.mapper.UserMapper;
import com.zhangtory.wayhomeadmin.model.entity.User;
import com.zhangtory.wayhomeadmin.model.request.LoginRequest;
import com.zhangtory.wayhomeadmin.model.request.UserRegisterRequest;
import com.zhangtory.wayhomeadmin.service.IUserService;
import com.zhangtory.wayhomeadmin.utils.JwtUtils;
import com.zhangtory.wayhomeadmin.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * @author zhangtory
 * @date 2020/6/23 20:45
 * @description: 用户相关接口
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param request
     */
    @Override
    public String register(UserRegisterRequest request) {
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new UserException(ResultCode.RE_PASSWORD_NOT_SAME);
        }
        User registerUser = UserHelper.getRegisterUser(request);
        try {
            userMapper.insert(registerUser);
        } catch (DuplicateKeyException e) {
            throw new UserException(ResultCode.USER_EXISTS);
        }
        return JwtUtils.createToken(registerUser);
    }

    /**
     * 用户登录
     *
     * @param request
     * @return token
     */
    @Override
    public String login(LoginRequest request) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", request.getUsername());
        User user = userMapper.selectOne(wrapper);
        if (user != null && PasswordUtils.checkPassword(request.getPassword(), user.getPassword())) {
            // 用户名密码匹配，创建token
            String token = JwtUtils.createToken(user);
            return token;
        }
        throw new UserException(ResultCode.USER_OR_PASSWORD_ERROR);
    }
}
