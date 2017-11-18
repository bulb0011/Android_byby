package com.android.www.myapplication.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class GetWin {

    Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;

    Application mApplication;

    private final float designWidth = 1080;


    public GetWin(Application application) {

        this.mApplication=application;

        activityLifecycleCallbacks=new Application.ActivityLifecycleCallbacks() {


            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                resetDensity(activity,designWidth);
                resetDensity(mApplication,designWidth);

            }

            @Override
            public void onActivityStarted(Activity activity) {

                resetDensity(activity,designWidth);
                resetDensity(mApplication,designWidth);

            }

            @Override
            public void onActivityResumed(Activity activity) {
                resetDensity(activity,designWidth);
                resetDensity(mApplication,designWidth);

                //获得当前的activity的name
               activity.getLocalClassName();

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        };

    }

    /**
     * 重新计算displayMetrics.xhdpi, 使单位pt重定义为设计稿的相对长度
     *
     * @param context
     * @param designWidth 设计稿的宽度
     * @see #activate()
     */
    public static void resetDensity(Context context, float designWidth) {
        if (context == null)
            return;

        Point size = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(size);

        Resources resources = context.getResources();

        resources.getDisplayMetrics().xdpi = size.x / designWidth * 72f;

        DisplayMetrics metrics = getMetricsOnMiui(context.getResources());
        if (metrics != null)
            metrics.xdpi = size.x / designWidth * 72f;
    }

    /**
     * 恢复displayMetrics为系统原生状态，单位pt恢复为长度单位磅
     *
     * @param context
     * @see #inactivate()
     */
    public static void restoreDensity(Context context) {
        context.getResources().getDisplayMetrics().setToDefaults();

        DisplayMetrics metrics = getMetricsOnMiui(context.getResources());
        if (metrics != null)
            metrics.setToDefaults();
    }

    //解决MIUI更改框架导致的MIUI7+Android5.1.1上出现的失效问题(以及极少数基于这部分miui去掉art然后置入xposed的手机)
    private static DisplayMetrics getMetricsOnMiui(Resources resources) {
        if ("MiuiResources".equals(resources.getClass().getSimpleName()) || "XResources".equals(resources.getClass().getSimpleName())) {
            try {
                Field field = Resources.class.getDeclaredField("mTmpMetrics");
                field.setAccessible(true);
                return (DisplayMetrics) field.get(resources);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 激活本方案
     */
    public void activate() {
        resetDensity(mApplication, designWidth);
        mApplication.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * 恢复系统原生方案
     */
    public void inactivate() {
        restoreDensity(mApplication);
        mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
}
