package com.zhangtory.wayhome.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangYaoYu
 * @date 2019/12/13 21:25
 */
@Getter
@AllArgsConstructor
public enum KeyDelEnum {
    /**
     * 未删除
     */
    UN_DELETE(0),
    /**
     * 已删除
     */
    DELETED(1);

    private Integer del;
}
