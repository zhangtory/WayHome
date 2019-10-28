package com.zhangtory.wayhome.service;

import com.zhangtory.wayhome.model.response.UserKeyResp;

import java.util.List;

public interface IKeyService {

    /**
     * 申请appID
     * @param username
     * @return
     */
    UserKeyResp applyUserKey(String username);

    /**
     * 删除key
     * @param username
     * @param appId
     */
    void deleteUserKey(String username, String appId);

    /**
     * 查询用户的所有key
     * @param username
     * @return
     */
    List<UserKeyResp> queryAllUserKey(String username);

}
