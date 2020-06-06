package com.zhangtory.wayhome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangtory.wayhome.config.UserContext;
import com.zhangtory.wayhome.constant.ExceptionConstant;
import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.exception.UserException;
import com.zhangtory.wayhome.mapper.UserMapper;
import com.zhangtory.wayhome.model.request.LoginRequest;
import com.zhangtory.wayhome.model.request.ResetPasswordRequest;
import com.zhangtory.wayhome.model.request.UserRegisterRequest;
import com.zhangtory.wayhome.service.IUserService;
import com.zhangtory.wayhome.utils.BeanUtils;
import com.zhangtory.wayhome.utils.JwtUtils;
import com.zhangtory.wayhome.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangtory
 * @since 2019-12-01
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        if (!userRegisterRequest.getPassword().equals(userRegisterRequest.getRepassword())) {
            throw new UserException(ExceptionConstant.RE_PASSWORD_NOT_SAME);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterRequest, user);
        user.setPassword(PasswordUtils.getEncryptedPassword(userRegisterRequest.getPassword()));
        try {
            this.save(user);
        } catch (DuplicateKeyException e) {
            throw new UserException(ExceptionConstant.USERNAME_EXIST);
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User user = lambdaQuery().eq(User::getUsername, loginRequest.getUsername()).one();
        if (user != null) {
            if (PasswordUtils.checkPassword(loginRequest.getPassword(), user.getPassword())) {
                // 用户名密码匹配，创建token
                String token = JwtUtils.createToken(user);
                return token;
            }
        }
        throw new UserException(ExceptionConstant.USER_OR_PASSWORD_ERROR);
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getReNewPassword())) {
            throw new UserException(ExceptionConstant.RE_PASSWORD_NOT_SAME);
        }
        // 检查旧密码是否正确
        User user = lambdaQuery().eq(User::getId, UserContext.getUserId()).one();
        if (PasswordUtils.checkPassword(resetPasswordRequest.getOldPassword(), user.getPassword())) {
            // 旧密码正确，修改密码
            user.setPassword(PasswordUtils.getEncryptedPassword(resetPasswordRequest.getNewPassword()));
            this.updateById(user);
            return;
        }
        throw new UserException(ExceptionConstant.PASSWORD_ERROR);
    }

}
