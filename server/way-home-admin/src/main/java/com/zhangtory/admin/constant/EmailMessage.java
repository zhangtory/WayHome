package com.zhangtory.admin.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 13:43
 * @Description: 邮件内容枚举
 */
@Getter
@AllArgsConstructor
public enum EmailMessage {
    /**
     * 通过邮箱重置密码
     */
    FIND_ACCOUNT("【WayHome】用户密码找回","请点击链接重置密码：{$url}");

    private String title;
    private String text;
}
