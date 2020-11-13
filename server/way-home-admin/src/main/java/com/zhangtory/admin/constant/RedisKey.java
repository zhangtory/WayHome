package com.zhangtory.admin.constant;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 14:23
 * @Description:
 */
public class RedisKey {

    /**
     * 用户找回密码
     */
    public static final String USER_FIND_ACCOUNT_KEY = "wayhome:key:account:find:${secret}";

    /**
     * 用户找回密码申请标志(hash)
     */
    public static final String USER_FIND_ACCOUNT_FLAG_KEY = "wayhome:key:account:find:flag";

    /**
     * keyAddress信息前缀
     * wayhome:key:{username}:{keyName}
     */
    public static final String KEY_ADDRESS_REDIS_KEY_PREFIX = "wayhome:key:${username}:${keyName}";

}
