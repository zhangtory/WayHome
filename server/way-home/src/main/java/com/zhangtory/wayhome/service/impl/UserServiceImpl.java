package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.dao.UserRepository;
import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.exception.PasswordErrorException;
import com.zhangtory.wayhome.exception.UserExistsException;
import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:03
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(UserRegisterReq req) {
        if (!req.getPassword().equals(req.getRepassword())) {
            throw new PasswordErrorException("两次密码不一致");
        }
        User user = new User();
        BeanUtils.copyProperties(req, user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        try {
            // username为唯一索引，如果用户名已存在会报异常
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserExistsException("用户名已存在");
        }
    }

}
