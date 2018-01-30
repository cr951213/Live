package com.example.maohuawei.live.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.geek.thread.GeekThreadManager;

public class App extends Application {


    private static final String TAG = App.class.getSimpleName();

    private static App INSTANCE;

    private static Handler handler = null;

    private static GeekThreadManager threadManager;

    private static boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();


        INSTANCE = this;

        // 初始化线程池
        threadManager = GeekThreadManager.getInstance();

        //Application通过此接口提供了一套回调方法，用于让开发者对Activity的生命周期事件进行集中处理
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);

        //初始化工具类
        Utils.init(this);


    }

    public static Context getAppContext() {
        return INSTANCE.getApplicationContext();
    }

    /**
     * 生命周期回掉
     */
    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            Log.i(TAG, "onActivityCreated: ");

        }

        @Override
        public void onActivityStarted(Activity activity) {


            Log.i(TAG, "onActivityStarted: ");
        }

        @Override
        public void onActivityResumed(Activity activity) {

            Log.i(TAG, "onActivityResumed: ");

        }

        @Override
        public void onActivityPaused(Activity activity) {

            Log.i(TAG, "onActivityPaused: ");
        }

        @Override
        public void onActivityStopped(Activity activity) {

            Log.i(TAG, "onActivityStopped: ");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            Log.i(TAG, "onActivitySaveInstanceState: ");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {

            Log.i(TAG, "onActivityDestroyed: ");
        }
    };


    /**
     * 获取线程池管理器
     *
     * @return
     */
    public static GeekThreadManager getThreadManager() {
        return threadManager;
    }

    public static Handler getHandler() {


        if (handler == null) {

            synchronized (App.class) {
                handler = new Handler();
            }
        }
        return handler;
    }
}
