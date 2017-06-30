/*
 * Copyright (C) 2012 The Health Platform Project
 *
 * 中民康寿网络科技发展有限公司. All rights reserved.
 */
package com.example.wsj.recyclerviewhelper;

/**
 * 定制的android.util.Log类，可以全局控制log打印
 */
public class Logcat {
    private static boolean DEBUG_ON = true;
    private static boolean ERROR_ON = true;
    private static boolean VERBOSE_ON = true;

    private static void setLogEnable(boolean enable) {
        DEBUG_ON = enable;
        ERROR_ON = enable;
        VERBOSE_ON = enable;
    }

    public static final String LOG_TAG = "Charles.utils";
    public static void v(String msg) {
        if (VERBOSE_ON) android.util.Log.v(LOG_TAG, msg);
    }

    public static void v(String msg, Throwable tr) {
        if (VERBOSE_ON) android.util.Log.v(LOG_TAG, msg, tr);
    }

    public static void e(String msg) {
        if (ERROR_ON) android.util.Log.e(LOG_TAG, msg);
    }

    public static void e(String msg, Throwable tr) {
        if (ERROR_ON) android.util.Log.e(LOG_TAG, msg, tr);
    }

    /**
     * 仅限于调试用，默认TAG为{@link #LOG_TAG}
     *
     * @param msg 调试信息
     */
    public static void d(byte msg) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Byte.toString(msg));
    }

    public static void d(short msg) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Short.toString(msg));
    }

    public static void d(int msg) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Integer.toString(msg));
    }

    public static void d(long msg) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Long.toString(msg));
    }

    public static void d(float msg) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Float.toString(msg));
    }

    public static void d(double msg) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Double.toString(msg));
    }

    public static void d(char msg) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Character.toString(msg));
    }

    public static void d(boolean msg) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Boolean.toString(msg));
    }

    public static void d(Object msg) {
        if (msg == null) {
            if (DEBUG_ON) android.util.Log.d(LOG_TAG, "null");
        } else {
            if (DEBUG_ON) android.util.Log.d(LOG_TAG, msg.toString());
        }
    }

    /**
     * 仅限于调试用, 可以使用自定义TAG
     *
     * @param tag tag标签
     * @param msg 调试信息
     */
    public static void d(String tag, String msg) {
        if (DEBUG_ON) android.util.Log.d(tag, msg);
    }

    public static void d(String tag, byte msg) {
        if (DEBUG_ON) android.util.Log.d(tag, Byte.toString(msg));
    }

    public static void d(String tag, short msg) {
        if (DEBUG_ON) android.util.Log.d(tag, Short.toString(msg));
    }

    public static void d(String tag, int msg) {
        if (DEBUG_ON) android.util.Log.d(tag, Integer.toString(msg));
    }

    public static void d(String tag, long msg) {
        if (DEBUG_ON) android.util.Log.d(tag, Long.toString(msg));
    }

    public static void d(String tag, float msg) {
        if (DEBUG_ON) android.util.Log.d(tag, Float.toString(msg));
    }

    public static void d(String tag, double msg) {
        if (DEBUG_ON) android.util.Log.d(tag, Double.toString(msg));
    }

    public static void d(String tag, char msg) {
        if (DEBUG_ON) android.util.Log.d(tag, Character.toString(msg));
    }

    public static void d(String tag, boolean msg) {
        if (DEBUG_ON) android.util.Log.d(tag, Boolean.toString(msg));
    }

    public static void d(String tag, Object msg) {
        if (DEBUG_ON) android.util.Log.d(tag, msg.toString());
    }

    /**
     * 仅限于调试用，可以使用自定义TAG
     *
     * @param tag tag标签
     * @param msg 调试信息
     * @param tr  一个异常可以打印日志出来
     */
    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, msg, tr);
    }

    public static void d(String tag, byte msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, Byte.toString(msg), tr);
    }

    public static void d(String tag, short msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, Short.toString(msg), tr);
    }

    public static void d(String tag, int msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, Integer.toString(msg), tr);
    }

    public static void d(String tag, long msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, Long.toString(msg), tr);
    }

    public static void d(String tag, float msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, Float.toString(msg), tr);
    }

    public static void d(String tag, double msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, Double.toString(msg), tr);
    }

    public static void d(String tag, char msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, Character.toString(msg), tr);
    }

    public static void d(String tag, boolean msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, Boolean.toString(msg), tr);
    }

    public static void d(String tag, Object msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(tag, msg.toString(), tr);
    }

    /**
     * 仅限于调试用，默认TAG为{@link #LOG_TAG}
     *
     * @param msg 调试信息
     * @param tr  一个异常可以打印日志出来
     */
    public static void d(String msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, msg, tr);
    }

    public static void d(byte msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Byte.toString(msg), tr);
    }

    public static void d(short msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Short.toString(msg), tr);
    }

    public static void d(int msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Integer.toString(msg), tr);
    }

    public static void d(long msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Long.toString(msg), tr);
    }

    public static void d(float msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Float.toString(msg), tr);
    }

    public static void d(double msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Double.toString(msg), tr);
    }

    public static void d(char msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Character.toString(msg), tr);
    }

    public static void d(boolean msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, Boolean.toString(msg), tr);
    }

    public static void d(Object msg, Throwable tr) {
        if (DEBUG_ON) android.util.Log.d(LOG_TAG, msg.toString(), tr);
    }
}
