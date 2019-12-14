package com.zhangtory.wayhome.constant;

/**
 * @author ZhangYaoYu
 * @date 2019/12/7 20:35
 */
public class CodeConstant {

    public static final String SUCCESS_MSG = "success";

    public static final String FAILURE_MSG = "failure";

    public static final int SUCCESS_CODE = 0;

    public static final int FAILURE_CODE = -1;

    public static final String UNKNOWN = "unknown";

    /**
     * 参数验证错误
     */
    public static final String VALID_ERROR_MSG = "valid_error";

    /**
     * Header token
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * 时间戳允许的误差范围(毫秒ms)
     */
    public static final Long TIMESTAMP_RANGE = 5 * 1000L;

}
