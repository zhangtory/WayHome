package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangtory.wayhome.exception.UserException;
import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.service.IUserService;
import com.zhangtory.wayhome.utils.BeanUtils;
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
    public void register(UserRegisterReq userRegisterReq) {
        if (!userRegisterReq.getPassword().equals(userRegisterReq.getRepassword())) {
            throw new UserException("两次密码不一致");
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterReq, user);
        user.setPassword(PasswordUtils.getEncryptedPassword(userRegisterReq.getPassword()));
        try {
            this.save(user);
        } catch (DuplicateKeyException e) {
            throw new UserException("用户名已存在");
        }
    }

}
