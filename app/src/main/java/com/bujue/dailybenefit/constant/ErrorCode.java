package com.bujue.dailybenefit.constant;

/**
 * @author bujue
 * @date 16/12/13
 */
public final class ErrorCode {

    private ErrorCode(){}

    public static final int NORMAL = 0;                 // 正常状态
    public static final int PARAMS_ERROR = -1;          // 参数错误
    public static final int RESULT_EXCEPTION = -2;      // 请求异常
    public static final int RESULT_NULL = -3;           // 请求结果为空
}
