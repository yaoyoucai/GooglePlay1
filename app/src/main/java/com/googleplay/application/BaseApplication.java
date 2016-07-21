package com.googleplay.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 13:48
 * 描述：application基类  定义并获取一些全局性的变量
 */
public class BaseApplication extends Application {
    private static Context mBaseApplicationContext;
    private static Handler mBaseHandler;
    private static Thread mMainThread;
    private static int mMainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplicationContext = getApplicationContext();
        mBaseHandler = new Handler();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
    }

    public static Context getBaseApplicationContext() {
        return mBaseApplicationContext;
    }

    public static Handler getBaseHandler() {
        return mBaseHandler;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }
}
