package com.zhangtory.wayhomeadmin.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author zhangtory
 * @date 2020/6/6 18:00
 * @description: 异常信息处理工具
 */
public class ExceptionUtils {

    /**
     * 返回错误的堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
        } finally {
            pw.close();
        }
        return sw.toString();
    }

}
