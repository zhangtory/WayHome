package com.zhangtory.wayhome.enums;

/**
 * @author ZhangYaoYu
 * @date 2019/12/13 21:25
 */
public enum KeyDelEnum {
    /**
     * 未删除
     */
    UnDelete(0),
    /**
     * 已删除
     */
    Deleted(1);

    private Integer del;
    KeyDelEnum(Integer del) {
        this.del = del;
    }

    public Integer getDel() {
        return del;
    }

}
