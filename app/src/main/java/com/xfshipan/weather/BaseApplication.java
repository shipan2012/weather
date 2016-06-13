package com.xfshipan.weather;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.xfshipan.weather.utils.FileUtils;

/**
 * Created by Administrator on 2015/9/20.
 */
public class BaseApplication extends Application {
    private static Application application;
    private static int mainThreadId;//主线程号
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        //复制数据库
        FileUtils.copyDB("city.db");
        application = this;
        mainThreadId = android.os.Process.myTid();
        handler = new Handler();
    }


    public static Context getApplication() {
        return application;
    }

    //获取主线程的Handler
    public static Handler getHandler() {
        return handler;
    }

    //获取主线程所在的进程号
    public static int getMainThreadId() {
        return mainThreadId;
    }

}
