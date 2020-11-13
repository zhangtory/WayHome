package com.zhangtory.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangtory.admin.constant.EmailMessage;
import com.zhangtory.admin.constant.RedisKey;
import com.zhangtory.admin.constant.UserResult;
import com.zhangtory.admin.dao.WhUserMapper;
import com.zhangtory.admin.model.entity.WhUser;
import com.zhangtory.admin.model.request.AccountFindRequest;
import com.zhangtory.admin.model.request.LoginRequest;
import com.zhangtory.admin.model.request.ResetPasswordRequest;
import com.zhangtory.admin.model.request.UserRegisterRequest;
import com.zhangtory.admin.service.IMailService;
import com.zhangtory.admin.service.IUserService;
import com.zhangtory.core.exception.CommonException;
import com.zhangtory.core.util.PasswordUtils;
import com.zhangtory.jwt.component.JwtHelper;
import com.zhangtory.jwt.component.UserContext;
import com.zhangtory.jwt.model.JwtUserVo;
import com.zhangtory.redis.contant.RedisTimeConstant;
import com.zhangtory.redis.service.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

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

    @Autowired
    private IMailService mailService;

    @Autowired
    private RedisHelper redisHelper;

    @Value("${url.user.find}")
    private String userAccountFindUrl;

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

    /**
     * 找回密码-发送邮件
     */
    @Override
    public void findAccountSendMail(String email) {
        // 验证该email是否在重置流程中
        String secret = redisHelper.getHashValue(RedisKey.USER_FIND_ACCOUNT_FLAG_KEY, email, String.class);
        if (StringUtils.isEmpty(secret)) {
            // 生成重置密码的secret
            String redisKey = RedisKey.USER_FIND_ACCOUNT_KEY.replace("${secret}", secret);
            secret = UUID.randomUUID().toString().replace("-", "");
            redisHelper.set(redisKey, email, RedisTimeConstant.ONE_DAY);
            redisHelper.addInMap(RedisKey.USER_FIND_ACCOUNT_FLAG_KEY, email, secret, RedisTimeConstant.ONE_DAY);
        }
        mailService.sendMail(email, EmailMessage.FIND_ACCOUNT.getTitle(),
                EmailMessage.FIND_ACCOUNT.getText().replace("${url}", userAccountFindUrl + secret));
    }

    /**
     * 找回密码-重置密码
     *
     * @param secret
     */
    @Override
    public void findAccount(String secret, AccountFindRequest request) {
        // 验证secret并获取email
        String email = checkAccountFindSecret(secret);
        WhUser user = whUserMapper.selectOne(new QueryWrapper<WhUser>()
                .lambda().eq(WhUser::getEmail, email));
        if (user != null) {
            user.setPassword(PasswordUtils.getEncryptedPassword(request.getPassword()));
            whUserMapper.updateById(user);
        } else {
            throw new CommonException(UserResult.USER_NOT_EXISTS);
        }
    }

    /**
     * 验证账户找回秘钥是否可用
     *
     * @param secret
     */
    @Override
    public String checkAccountFindSecret(String secret) {
        String redisKey = RedisKey.USER_FIND_ACCOUNT_KEY.replace("${secret}", secret);
        String email = redisHelper.get(redisKey);
        if (StringUtils.isEmpty(email)) {
            throw new CommonException(UserResult.ACCOUNT_FIND_SECRET_ERROR);
        }
        return email;
    }

    /**
     * 重置密码
     */
    @Override
    public void resetPassword(ResetPasswordRequest request) {
        WhUser user = whUserMapper.selectOne(new QueryWrapper<WhUser>()
                .lambda().eq(WhUser::getUsername, userContext.getUsername()));
        if (user != null && PasswordUtils.checkPassword(request.getOldPassword(), user.getPassword())) {
            // 旧密码验证通过，设置新密码
            user.setPassword(PasswordUtils.getEncryptedPassword(request.getNewPassword()));
            whUserMapper.updateById(user);
        } else {
            throw new CommonException(UserResult.OLD_PASSWORD_ERROR);
        }
    }

}
