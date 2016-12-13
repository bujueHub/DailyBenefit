package com.bujue.dailybenefit.utils;

import android.util.Log;

/**
 * 日志工具类
 *
 * @author bujue
 * @date 16/10/17
 */
public class Logger {

    /**
     * 日志开关
     */
    private static boolean isDebug = true;

    /**
     * log.i
     */
    public static void i(String tag, String msg, Object... args) {
        String log = formatLog(msg, args);
        if (isDebug || Log.isLoggable(tag, Log.INFO)) {
            Log.i(tag, log);
        }
    }

    /**
     * log.v
     */
    public static void v(String tag, String msg, Object... args) {
        String log = formatLog(msg, args);
        if (isDebug || Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, log);
        }
    }

    /**
     * log.d
     */
    public static void d(String tag, String msg, Object... args) {
        String log = formatLog(msg, args);
        if (isDebug || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, log);
        }
    }

    /**
     * log.e
     */
    public static void e(String tag, String msg, Object... args) {
        String log = formatLog(msg, args);
        if (isDebug || Log.isLoggable(tag, Log.ERROR)) {
            Log.e(tag, log);
        }
    }


    /**
     * log.w
     */
    public static void w(String tag, String msg, Object... args) {
        String log = formatLog(msg, args);
        if (isDebug || Log.isLoggable(tag, Log.WARN)) {
            Log.w(tag, log);
        }
    }

    /**
     * 格式化输出文案
     *
     * @param format
     * @param args
     * @return
     */
    private static String formatLog(String format, Object... args) {
        if (format == null) {
            return "";
        }
        try {
            return String.format(format, args);
        } catch (Exception e) {
            return "";
        }
    }

}
