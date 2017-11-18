package com.android.www.myapplication;

import android.view.View;
import android.widget.Button;

import com.android.www.myapplication.ben.ArticleBean;
import com.android.www.myapplication.ben.ArticleBeanForCache;
import com.android.www.myapplication.ben.MergeData;
import com.android.www.myapplication.ben.ResultData;
import com.android.www.myapplication.network.NetCallbacks;
import com.android.www.myapplication.network.NetUtils;
import com.android.www.myapplication.utils.RxUtils;
import com.android.www.myapplication.utils.TypeUtils;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
@EActivity(R.layout.activity_main)
public class A extends BaseActivity {

    @ViewById(R.id.bt)
    Button bt;

    @AfterViews
    void init(){
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A.this.finish();
            }
        });

//        getInfo();


        Net();

//        TT();


    }

    private void Net() {
        Map<String, String> params = new HashMap<>();
//        User mainUser = UserCache.getMainUser(this);
        params.put("userId", "43e7793a-d526-4a87-8d5b-4bf7f81f6c65");
        params.put("token", "7f0cd0f4-e46d-40e7-aaab-891cf87c256d");

        NetUtils.get("http://123.15.47.26:8080/zhiyinshou/allb/getAllbAndJfbkByRand.do",
                getClass().getCanonicalName(), params, new NetCallbacks<MergeData>() {

                    private List<ArticleBeanForCache> jkxts;
                    private List<ArticleBeanForCache> bxj;

                    @Override
                    public void onSuccess(ResultData<MergeData> response) {

                        if (response.result==1) {

                            MergeData mergeData=response.data;

                            bxj =mergeData.bxj;
                            jkxts =mergeData.jkxts;

                        }

                    }

                    @Override
                    public void onErr(String errMsg) {

                    }
                },this ,true,MergeData.class);
    }


    /**
     * 向服务器请求变形记 & 健康小贴士
     */
    void getInfo() {

        HttpParams params = new HttpParams();
//        User mainUser = UserCache.getMainUser(this);
        params.put("userId", "43e7793a-d526-4a87-8d5b-4bf7f81f6c65");
        params.put("token", "7f0cd0f4-e46d-40e7-aaab-891cf87c256d");


        Type type = TypeUtils.INSTANCE.getType(MergeData.class);
        /**
         * 带缓存请求示例，勿删除
         * 带缓存请求示例，勿删除
         * 带缓存请求示例，勿删除
         */
        Observable<Response<ResultData<MergeData>>> observable = RxUtils.requestWithCache(HttpMethod.POST,
                "http://123.15.47.26:8080/zhiyinshou/allb/getAllbAndJfbkByRand.do",
                 type, params,
                 "http://123.15.47.26:8080/zhiyinshou/allb/getAllbAndJfbkByRand.do");

//        Observable<Response<ResultData<ArticleBean>>> observable=RxUtils.request(HttpMethod.POST,
//                "http://123.15.47.26:8080/zhiyinshou/allb/getAllbInfo.do",
//                type,
//                params
//        );

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(provider.<Response<ResultData<MergeData>>>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Observer<Response<ResultData<MergeData>>>() {

                    private List<ArticleBeanForCache> jkxts;
                    private List<ArticleBeanForCache> bxj;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ResultData<MergeData>> data) {
                        if (!data.isFromCache()) {
                            if (data.body().result == 99) {
//                                NetUtils.onTokenInvalid(getActivity());
                                return;
                            }
                        }
                        ResultData<MergeData> result = data.body();
                        if (result.data != null) {
                            bxj =result.data.bxj;
                            jkxts =result.data.jkxts;
                        }

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    void TT(){

        HttpParams params = new HttpParams();
//        User mainUser = UserCache.getMainUser(this);
        params.put("userId", "43e7793a-d526-4a87-8d5b-4bf7f81f6c65");
        params.put("token", "7f0cd0f4-e46d-40e7-aaab-891cf87c256d");
        params.put("pageindex", "1");
        params.put("pagesize", "10");

        Type type = TypeUtils.INSTANCE.getListType(ArticleBean.class);

        Observable<ResultData<List<ArticleBean>>> observable=RxUtils.request(HttpMethod.POST,
                "http://123.15.47.26:8080/zhiyinshou/allb/getAllbInfo.do",
                type,
                params
        );

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultData<List<ArticleBean>>>() {

                    private List<ArticleBean> s;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultData<List<ArticleBean>> articleBeanResultData) {

                        s =articleBeanResultData.data;

                        String sa=s.get(1).dataName;

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
