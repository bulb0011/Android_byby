package com.android.www.myapplication.network;


import com.android.www.myapplication.ben.ResultData;

/**
 * Created by Administrator on 2017/8/14.
 */

public interface NetCallbacks2<T> extends NetCallbacks {

    void onCaheSuccess(ResultData<T> response);

}
