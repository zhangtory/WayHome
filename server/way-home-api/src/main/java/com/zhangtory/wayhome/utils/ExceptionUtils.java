package com.zhangtory.wayhome.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author ZhangYaoYu
 * @date 2020/6/6 18:00
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
