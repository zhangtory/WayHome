package com.zhangtory.wayhome.service.impl;

import com.zhangtory.wayhome.dao.UserKeyRepository;
import com.zhangtory.wayhome.dao.UserRepository;
import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.entity.UserKey;
import com.zhangtory.wayhome.exception.PasswordErrorException;
import com.zhangtory.wayhome.exception.UserExistsException;
import com.zhangtory.wayhome.model.dto.UserDto;
import com.zhangtory.wayhome.model.dto.UserKeyDto;
import com.zhangtory.wayhome.model.request.ChangePasswordReq;
import com.zhangtory.wayhome.model.request.UserRegisterReq;
import com.zhangtory.wayhome.model.response.DashboardResp;
import com.zhangtory.wayhome.service.IUserService;
import com.zhangtory.wayhome.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:03
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserKeyRepository userKeyRepository;

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

    @Override
    public void changePassword(ChangePasswordReq req, String username) {
        if (!req.getNewPassword().equals(req.getReNewPassword())) {
            throw new PasswordErrorException("两次密码不一致");
        }
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UserExistsException("用户不存在");
        }
        if (new BCryptPasswordEncoder().matches(req.getOldPassword(), user.getPassword())) {
            // 密码正确，可以重置
            String encrypNewPassword = new BCryptPasswordEncoder().encode(req.getNewPassword());
            user.setPassword(encrypNewPassword);
            userRepository.save(user);
        } else {
            throw new PasswordErrorException("旧密码错误");
        }
    }

    @Override
    public DashboardResp getDashboardInfo(String username) {
        DashboardResp dashboardResp = new DashboardResp();
        // userInfo
        User user = userRepository.getByUsername(username);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        dashboardResp.setUserInfo(userDto);
        // userKey
        List<UserKey> userKeyList = userKeyRepository.findAllByUserId(user.getId());
        List<UserKeyDto> userKeyDtoList = new ArrayList<>();
        BeanUtils.copyProperties(userKeyList, userKeyDtoList);
        dashboardResp.setUserKeyList(userKeyDtoList);
        return dashboardResp;
    }

}
