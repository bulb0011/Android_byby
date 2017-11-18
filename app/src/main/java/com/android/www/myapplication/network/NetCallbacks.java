package com.android.www.myapplication.network;


import com.android.www.myapplication.ben.ResultData;

/**
 * 定义网络的数据回调
 * Created by gcy on 2017/8/1 0001.
 */

public interface NetCallbacks<T> {
    void onSuccess(ResultData<T> response);

    void onErr(String errMsg);
}
