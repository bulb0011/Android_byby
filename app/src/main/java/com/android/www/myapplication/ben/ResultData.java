package com.android.www.myapplication.ben;

import java.io.Serializable;

/**
 * 服务器返回数据vo
 * Created by gcy on 2017/8/8 0008.
 */

public class ResultData<T> implements Serializable {
    public int result;//0失败 1成功 99token无效 其他值定义按接口来
    public String msg;
    public int totalPageSize;
    public T data;
}
