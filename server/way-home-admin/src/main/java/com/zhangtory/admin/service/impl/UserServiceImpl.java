package com.zhangtory.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangtory.admin.constant.UserResult;
import com.zhangtory.admin.dao.WhUserMapper;
import com.zhangtory.admin.model.entity.WhUser;
import com.zhangtory.admin.model.request.LoginRequest;
import com.zhangtory.admin.model.request.UserRegisterRequest;
import com.zhangtory.admin.service.IUserService;
import com.zhangtory.core.exception.CommonException;
import com.zhangtory.core.util.PasswordUtils;
import com.zhangtory.jwt.component.JwtHelper;
import com.zhangtory.jwt.component.UserContext;
import com.zhangtory.jwt.model.JwtUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @author zhangtory
 * @date 2020/6/23 20:45
 * @description: 用户相关接口
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private WhUserMapper whUserMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserContext userContext;

    /**
     * 用户注册
     *
     * @param request
     */
    @Override
    public String register(UserRegisterRequest request) {
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new CommonException(UserResult.RE_PASSWORD_NOT_SAME);
        }
        WhUser registerUser = request.getWhUser();
        try {
            whUserMapper.insert(registerUser);
        } catch (DuplicateKeyException e) {
            throw new CommonException(UserResult.USER_EXISTS);
        }
        JwtUserVo jwtUserVo = new JwtUserVo();
        BeanUtils.copyProperties(registerUser, jwtUserVo);
        return jwtHelper.createToken(jwtUserVo);
    }

    /**
     * 用户登录
     *
     * @param request
     * @return token
     */
    @Override
    public String login(LoginRequest request) {
        WhUser user = whUserMapper.selectOne(new QueryWrapper<WhUser>()
                .lambda().eq(WhUser::getUsername, request.getUsername()));
        if (user != null && PasswordUtils.checkPassword(request.getPassword(), user.getPassword())) {
            // 用户名密码匹配，创建token
            JwtUserVo jwtUserVo = new JwtUserVo();
            BeanUtils.copyProperties(user, jwtUserVo);
            String token = jwtHelper.createToken(jwtUserVo);
            return token;
        }
        throw new CommonException(UserResult.USER_OR_PASSWORD_ERROR);
    }

    @Override
    public void findAccount() {

    }

    /**
     * 重置密码
     */
    @Override
    public void resetPassword() {
        System.out.println(userContext.getUsername());
    }

}
