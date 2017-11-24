package com.android.www.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.android.www.myapplication.ben.MergeData;
import com.android.www.myapplication.ben.ResultData;
import com.android.www.myapplication.network.NetUtils;
import com.android.www.myapplication.utils.RxUtils;
import com.android.www.myapplication.utils.TypeUtils;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private WaveView waveView;

    private CountDownTimer timer;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waveView = (WaveView)findViewById(R.id.yuan);

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A_.intent(MainActivity.this).start();
            }
        });

//        HttpParams params = new HttpParams();

        waveView.settoContext(this);

        waveView.setDuration(5000);
        //waveView.setStyle(Paint.Style.STROKE);
        waveView.setStyle(Paint.Style.FILL);
        waveView.setSpeed(400);
        waveView.setColor(Color.parseColor("#ff0000"));
        waveView.setInterpolator(new AccelerateInterpolator(1.2f));
        waveView.start();

        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {

                waveView.stop();

//                A_.intent(MainActivity.this).start();

//                bbbb.stp();

                Intent intent=new Intent();

                intent.setClass(MainActivity.this,A_.class);

                startActivityForResult(intent,1009);
            }

        };

        timer.start();

        Intent intent=new Intent();

        intent.setClass(this,A_.class);

        startActivityForResult(intent,1009);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case 1009:
                MergeData mergeData =(MergeData)data.getSerializableExtra("data");
                Toast.makeText(this,"1009"+"="+mergeData.bxj.size(),Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"1009"+"="+mergeData.jkxts.size(),Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"1009",Toast.LENGTH_SHORT).show();
                break;

        }

    }

    /**
     * (
     * 向服务器请求变形记 & 健康小贴士
     */
    void getInfo() {

        Type type = TypeUtils.INSTANCE.getType(MergeData.class);
        HttpParams params = new HttpParams();
//        User mainUser = UserCache.getMainUser(this);
        params.put("userId", "43e7793a-d526-4a87-8d5b-4bf7f81f6c65");
        params.put("token", "7f0cd0f4-e46d-40e7-aaab-891cf87c256d");

        /**
         * 带缓存请求示例，勿删除
         * 带缓存请求示例，勿删除
         * 带缓存请求示例，勿删除
         */
        Observable<Response<ResultData<MergeData>>> observable = RxUtils.requestWithCache(HttpMethod.POST,
                "http://123.15.47.26:8080/zhiyinshou/allb/getAllbAndJfbkByRand.do", type, params, "http://123.15.47.26:8080/zhiyinshou/allb/getAllbAndJfbkByRand.do");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(provider.<Response<ResultData<MergeData>>>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribe(new Observer<Response<ResultData<MergeData>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        LogUtils.e("start subscribe");
                    }

                    @Override
                    public void onNext(@NonNull Response<ResultData<MergeData>> data) {
                        // LogUtils.e(data.isFromCache() + "-->");
                        if (!data.isFromCache()) {
                            if (data.body().result == 99) {
                                NetUtils.onTokenInvalid(MainActivity.this);
                                return;
                            }
                        }
                        ResultData<MergeData> result = data.body();
                        if (result.data != null) {
//                            fillBianXing(result.data.bxj);
//                            fillHealth(result.data.jkxts);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
//                        LogUtils.e("get main fail:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
