package com.bujue.dailybenefit.callback;

import android.os.Handler;
import android.os.Looper;

/**
 * Callback封装, 回调保证在主线程
 *
 * @date 16/1/7
 * @email tufu@mogujie.com
 */
public final class MainThreadCallback {

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    private MainThreadCallback() {
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            mHandler.post(runnable);
        }
    }

    public static void onException(final Callback<?> callback, final int code, final String reason) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        });
    }


    public static <T> void onSuccess(final Callback<T> callback, final T object) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (callback != null) {
                    callback.onSuccess(object);
                }
            }
        });
    }
}
