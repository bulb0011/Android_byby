package com.android.www.myapplication.app;

import android.app.Application;

import com.android.www.myapplication.utils.GetWin;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/11/15 0015.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new GetWin(this).activate();


        initNet();

    }

    void initNet() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 全局的读取超时时间
        builder.readTimeout(5000, TimeUnit.MILLISECONDS);
        // 全局的写入超时时间
        builder.writeTimeout(5000, TimeUnit.MILLISECONDS);
        // 全局的连接超时时间
        builder.connectTimeout(5000, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);

        //if (BuildConfig.DEBUG)
        builder.addInterceptor(loggingInterceptor);

        //非必要情况，不建议使用，第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(this));

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build());
    }


}
