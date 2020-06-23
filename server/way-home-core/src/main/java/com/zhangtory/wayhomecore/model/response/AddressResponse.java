package com.zhangtory.wayhomecore.model.response;

import lombok.Data;

/**
 * @author: zhangtory
 * @date: 10/25 17:27
 * @description: 地址查询返回对象
 */
@Data
public class AddressResponse {

    private String protocol;

    private String ip;

    private int port;

    private String path;

    private String url;

}
