package com.doyo.sdk.global;

import android.app.Application;
import android.os.Handler;


/**
 * @author : 高磊华
 * @date : 2019\6\3
 * e-mail : 984992087@qq.com
 * desc   : 全局Application
 */

public class GlobalApplication extends Application {

    protected static Handler           handler;
    protected static int               mainThreadId;
    public static    GlobalApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
    }

    /**
     * 获取上下文对象
     *
     * @return context
     */

    public static GlobalApplication getInstance() {
        return mInstance;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }
}
