package com.android.www.myapplication.network;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.www.myapplication.ben.ResultData;
import com.android.www.myapplication.ben.User;
import com.android.www.myapplication.db.DBHelper;
import com.android.www.myapplication.utils.UserCache;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import ikidou.reflect.TypeBuilder;



/**
 * Created by Administrator on 2017/11/15 0015.
 */

public class NetUtils {

    public static void setContext(Context context) {
        NetUtils.context = context;
    }

    private static Context context;

    private static final String TAG = "NetUtils";


    public static void onTokenInvalid(Activity activity) {
        UserCache.clear();
        if (activity != null) {
            //说明token已过期，需要重新登录
//            CommonUtils.showToast((BLKApp) activity.getApplicationContext(), "账户已经在其他地方登录，请重新登录");
            /**
             * 此时删除数据库容易造成崩溃，所以将删除逻辑放到登录片段中
             */
//            try {
//                DBHelper.getDbManager(activity.getApplicationContext()).delete(User.class);
//            } catch (Exception e) {
//            }
            restartApp(activity);
        }
    }

    public static void onTokenInvalid(final Context context) {
        UserCache.clear();
        //说明token已过期，需要重新登录
        if (context != null) {
            Toast.makeText(context.getApplicationContext(), "账户已经在其他地方登录，请重新登录", Toast.LENGTH_SHORT).show();

            try {
                DBHelper.getDbManager(context.getApplicationContext()).delete(User.class);
            } catch (Exception e) {
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        //发送广播
//                        context.sendBroadcast(new Intent(Constant.ACTION_EXIT_APP));
//                        BasicActivity_.intent(context).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void restartApp(final Context activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    ////发送广播
//                    activity.sendBroadcast(new Intent(Constant.ACTION_EXIT_APP));
//                    BasicActivity_.intent(activity).start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * @param url
     * @param tag
     */
    public static <T> void get(String url, String tag, Map<String, String> params, final NetCallbacks<T> callbacks,
                               final Context activity, boolean isPost, Class clazz) {
        Request request;
        if (isPost) {
            request = OkGo.<ResultData>post(url)
                    .tag(tag);
        } else {
            request = OkGo.<ResultData>get(url)
                    .tag(tag);
        }
        if (params != null && params.size() > 0) {
            request.params(params, true);
        }

        Type type = TypeBuilder.newInstance(ResultData.class).addTypeParam(clazz).build();
        Log.i(TAG, "url = " + url);
        Log.i(TAG, params.toString());

        request.execute(new GsonCallback<ResultData<T>>(type, (activity instanceof Activity) ? (Activity) activity : null) {
            @Override
            public void onSuccess(Response<ResultData<T>> response) {
                String resp = response.toString();
//                LogUtils.i("response = " + resp);

                if (response.body().result == 99) {
                    onTokenInvalid(context);
                } else {
                    if (callbacks != null)
                        callbacks.onSuccess(response.body());
                }
            }

            @Override
            public void onError(Response<ResultData<T>> response) {
                super.onError(response);
                if (callbacks != null)
                    callbacks.onErr(response.message());
            }

        });

//
//        request.execute(new GsonCallback<ResultData<T>>(new TypeToken<ResultData<T>>() {
//        }.getType(), activity) {
//            @Override
//            public void onSuccess(Response<ResultData<T>> response) {
//                if (callbacks != null)
//                    callbacks.onSuccess(response.body());
//            }
//
//            @Override
//            public void onError(Response<ResultData<T>> response) {
//                super.onError(response);
//                if (callbacks != null)
//                    callbacks.onErr(response.message());
//            }
//
//        });

//        request.execute(new BLKStringCallback(activity) {
//            @Override
//            public void onSuccess(Response<String> response) {
//                if (callbacks != null)
//                    callbacks.onSuccess(response.body());
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                if (callbacks != null)
//                    callbacks.onErr(response.message());
//            }
//        });

    }

    /**
     * 适用于返回一个list数组的情况
     *
     * @param url
     * @param tag
     */
    public static <T> void getList(String url, String tag, Map<String, String> params, final NetCallbacks<T> callbacks,
                                   final Context activity, boolean isPost, Class clazz) {
        Request request;
        if (isPost) {
            request = OkGo.<ResultData>post(url)
                    .tag(tag);
        } else {
            request = OkGo.<ResultData>get(url)
                    .tag(tag);
        }
        if (params != null && params.size() > 0) {
            request.params(params, true);
        }

       /* Example for Map<String, List<String>>

        Type type = TypeBuilder
                .newInstance(Map.class)
                .addTypeParam(String.class)
                .beginSubType(List.class) //开始 List<String> 部分
                .addTypeParam(String.class) //设置List的泛型值
                .endSubType() //结束 List<String> 部分
                .build();*/

        Type type = TypeBuilder.newInstance(ResultData.class)
                .beginSubType(List.class)
                .addTypeParam(clazz)
                .endSubType()
                .build();


        request.execute(new GsonCallback<ResultData<T>>(type, (activity instanceof Activity) ? (Activity) activity : null) {
            @Override
            public void onSuccess(Response<ResultData<T>> response) {
                if (response.body().result == 99) {
                    onTokenInvalid(context);
                } else {
                    if (callbacks != null)
                        callbacks.onSuccess(response.body());
                }
            }

            @Override
            public void onError(Response<ResultData<T>> response) {
                super.onError(response);
                if (callbacks != null)
                    callbacks.onErr(response.message());
            }
        });
//
//        request.execute(new GsonCallback<ResultData<T>>(new TypeToken<ResultData<T>>() {
//        }.getType(), activity) {
//            @Override
//            public void onSuccess(Response<ResultData<T>> response) {
//                if (callbacks != null)
//                    callbacks.onSuccess(response.body());
//            }
//
//            @Override
//            public void onError(Response<ResultData<T>> response) {
//                super.onError(response);
//                if (callbacks != null)
//                    callbacks.onErr(response.message());
//            }
//
//        });

//        request.execute(new BLKStringCallback(activity) {
//            @Override
//            public void onSuccess(Response<String> response) {
//                if (callbacks != null)
//                    callbacks.onSuccess(response.body());
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                if (callbacks != null)
//                    callbacks.onErr(response.message());
//            }
//        });

    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param url
     * @param tag
     * @param params
     * @param callbacks
     * @param activity
     * @param isPost
     * @param clazz
     * @param <T>
     */
    public static <T> void getList(String url, String tag, Map<String, String> params, final NetCallbacks2<T> callbacks,
                                   final Activity activity, boolean isPost, Class clazz, String key) {
        Request request;
        if (isPost) {
            request = OkGo.<ResultData>post(url)
                    .cacheKey(key)
                    .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                    .tag(tag);
        } else {
            request = OkGo.<ResultData>get(url)
                    .cacheKey(key)
                    .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                    .tag(tag);
        }
        if (params != null && params.size() > 0) {
            request.params(params, true);

        }

       /* Example for Map<String, List<String>>

        Type type = TypeBuilder
                .newInstance(Map.class)
                .addTypeParam(String.class)
                .beginSubType(List.class) //开始 List<String> 部分
                .addTypeParam(String.class) //设置List的泛型值
                .endSubType() //结束 List<String> 部分
                .build();*/

        Type type = TypeBuilder.newInstance(ResultData.class)
                .beginSubType(List.class)
                .addTypeParam(clazz)
                .endSubType()
                .build();


        request.execute(new GsonCallback<ResultData<T>>(type, activity) {
            @Override
            public void onSuccess(Response<ResultData<T>> response) {
                if (response.body().result == 99) {
                    //说明token已过期，需要重新登录
                    if (activity != null) {
                        try {
                            DBHelper.getDbManager(activity.getApplicationContext()).delete(User.class);
                        } catch (Exception e) {
                        }
//                        activity.sendBroadcast(new Intent(Constant.ACTION_EXIT_APP));
//                        BasicActivity_.intent(activity).start();
                    }
                } else {
                    if (callbacks != null)
                        callbacks.onSuccess(response.body());
                }
            }

            @Override
            public void onCacheSuccess(Response<ResultData<T>> response) {
                super.onCacheSuccess(response);

                if (callbacks != null)
                    callbacks.onCaheSuccess(response.body());
            }

            @Override
            public void onError(Response<ResultData<T>> response) {
                super.onError(response);
                if (callbacks != null)
                    callbacks.onErr(response.message());
            }

        });
//
//        request.execute(new GsonCallback<ResultData<T>>(new TypeToken<ResultData<T>>() {
//        }.getType(), activity) {
//            @Override
//            public void onSuccess(Response<ResultData<T>> response) {
//                if (callbacks != null)
//                    callbacks.onSuccess(response.body());
//            }
//
//            @Override
//            public void onError(Response<ResultData<T>> response) {
//                super.onError(response);
//                if (callbacks != null)
//                    callbacks.onErr(response.message());
//            }
//
//        });

//        request.execute(new BLKStringCallback(activity) {
//            @Override
//            public void onSuccess(Response<String> response) {
//                if (callbacks != null)
//                    callbacks.onSuccess(response.body());
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                if (callbacks != null)
//                    callbacks.onErr(response.message());
//            }
//        });

    }

    /**
     * @param url
     * @param tag
     */
    public static <T> void get(String url, String tag, Map<String, String> params, final NetCallbacks2<T> callbacks,
                               final Activity activity, boolean isPost, Class clazz, String key) {
        Request request;
        if (isPost) {
            request = OkGo.<ResultData>post(url)
                    .cacheKey(key)
                    .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                    .tag(tag);
        } else {
            request = OkGo.<ResultData>get(url)
                    .cacheKey(key)
                    .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                    .tag(tag);
        }
        if (params != null && params.size() > 0) {
            request.params(params, true);
        }

        Type type = TypeBuilder.newInstance(ResultData.class).addTypeParam(clazz).build();


        request.execute(new GsonCallback<ResultData<T>>(type, activity) {
            @Override
            public void onSuccess(Response<ResultData<T>> response) {
                if (response.body().result == 99) {
                    onTokenInvalid(activity);
                } else {
                    if (callbacks != null)
                        callbacks.onSuccess(response.body());
                }
            }

            @Override
            public void onCacheSuccess(Response<ResultData<T>> response) {
                super.onCacheSuccess(response);

                if (callbacks != null)
                    callbacks.onCaheSuccess(response.body());

            }

            @Override
            public void onError(Response<ResultData<T>> response) {
                super.onError(response);
                if (callbacks != null)
                    callbacks.onErr(response.message());
            }

        });
    }

    //////////////////////////////////////////////////
//    public static void download(String url, String tag, FileCallback callbacks) {
//        OkGo.<File>get(url)
//                .tag(tag)
//                .execute(callbacks);
//                .execute(new FileCallback(dir, path) {
//                    @Override
//                    public void onSuccess(Response<File> response) {
//                        // callbacks.onSuccess(response.body());
//                    }
//
//                    @Override
//                    public void onError(Response<File> response) {
//                        super.onError(response);
//                        //callbacks.onErr(response.message());
//                    }
//
//                    @Override
//                    public void downloadProgress(Progress progress) {
//                        super.downloadProgress(progress);
//                        // TODO: 2017/8/8 0008 下载进度，到时候根据页面需求做
//                    }
//                });
    //}

    public static void cancel(String tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    public static void request(String url, Map<String, String> params, final RequestListener rr, boolean isPost){
        Request request;
        if (isPost) {
            request = OkGo.<ResultData>post(url)
                    .cacheMode(CacheMode.DEFAULT);
        } else {
            request = OkGo.<ResultData>get(url)
                    .cacheMode(CacheMode.DEFAULT);
        }

        if (params != null && params.size() > 0) {
            request.params(params, true)
                    .tag(url + params.toString());
        }

        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                rr.onComplete(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                rr.onError(response.getException().getMessage());
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    public interface RequestListener{
        void onComplete(String result);
        void onError(String result);
    }



}
