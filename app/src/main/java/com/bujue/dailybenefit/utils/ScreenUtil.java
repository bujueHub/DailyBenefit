package com.bujue.dailybenefit.utils;

import android.content.Context;

/**
 * 屏幕分辨率工具类
 */
public class ScreenUtil {

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        return 0;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        return 0;
    }

    /**
     * DP转化为PX
     *
     * @param dip
     * @return
     */
    public static int dp2px(Context context, int dip) {
        if (context != null) {
            float density = context.getResources().getDisplayMetrics().density;
            return (int) (dip * density + 0.5);
        }
        return dip;
    }

    /**
     * PX转化为DP
     *
     * @param px
     * @return
     */
    public static int px2dp(Context context, int px) {
        if (context != null) {
            float density = context.getResources().getDisplayMetrics().density;
            return (int) ((px - 0.5) / density);
        }
        return px;
    }

    /**
     * 获取屏幕状态栏高度
     *
     * @return
     */
    public static int getScreenBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
    }
}