package com.bujue.dailybenefit.callback;

/**
 * @author bujue
 * @date 16/12/13
 */
public interface Callback<T> {

    /**
     * 返回成功回调
     *
     * @param result
     */
    void onSuccess(T result);


    /**
     * 返回失败回调
     *
     * @param code
     * @param reason
     */
    void onException(int code, String reason);
}
