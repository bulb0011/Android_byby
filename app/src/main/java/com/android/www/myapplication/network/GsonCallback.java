package com.android.www.myapplication.network;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 自定义gson回调，方便解析服务器数据
 * Created by gcy on 2017/7/18 0018.
 */

public abstract class GsonCallback<T> extends AbsCallback<T> {
    Type type;
    Class<T> clazz;

    KProgressHUD hud;

    public GsonCallback(Type type) {
        this.type = type;
    }

    public GsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public GsonCallback(Class<T> clazz, Activity activity) {
        this.clazz = clazz;
        initProgress(activity);
    }

    public GsonCallback(Type type, Activity activity) {
        this.type = type;
        initProgress(activity);
    }

    void initProgress(Activity activity) {
        if (activity != null)
            hud = KProgressHUD.create(activity)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(false)
                    // .setAutoDismiss(false)
                    ;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (hud != null && !hud.isShowing())
            hud.show();
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null)
            return null;
        T data = null;
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(body.charStream());

        if (type != null)
            data = gson.fromJson(reader, type);

        if (clazz != null)
            data = gson.fromJson(reader, clazz);


        return data;
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (hud != null && hud.isShowing())
            hud.dismiss();
    }
}
