package com.zhangtory.wayhome.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:27
 * @Description:
 */
@Data
@Builder
public class BaseResponse implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

}
