package com.zhangtory.wayhome.model.response;

import com.zhangtory.wayhome.model.dto.UserDto;
import com.zhangtory.wayhome.model.dto.UserKeyDto;
import lombok.Data;

import java.util.List;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/28 16:49
 * @Description:
 */
@Data
public class DashboardResp {

    private UserDto userInfo;
    private List<UserKeyDto> userKeyList;

}
