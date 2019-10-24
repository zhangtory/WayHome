package com.zhangtory.wayhome.model;

import lombok.Data;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: ZhangYaoYu
 * @Date: 10/23 16:38
 * @Description:
 */
@Data
public class SetWayHomeReq {

    /**
     * 时间戳，防重放攻击
     */
    private Long timestamp;

    /**
     * 签名，防止恶意调用
     */
    private String sign;

}
