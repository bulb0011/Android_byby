package com.android.www.myapplication.ben;

import com.google.gson.annotations.SerializedName;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * 健康管理师
 * 缓存的类必须实现Serializable接口
 * Created by gcy on 2017/10/8 0008.
 */
@Table(name = "hm")
public class HealthManager implements Serializable {

    /**
     * "createTime":"2017-11-03 15:08:15",
     * "head":"",
     * "id":10,
     * "name":"王雪凤",
     * "team":"北区客服部",
     * "updateTime":"2017-11-03 16:07:53",
     * "weixin":"qqt3455"
     */
    @SerializedName("name")
    @Column(name = "name")
    public String Name;

    @SerializedName("weixin")
    @Column(name = "wechat")
    public String WeChat;

    @SerializedName("team")
    @Column(name = "team")
    public String Team;

    @SerializedName("head")
    @Column(name = "pic")
    public String pic;

    @Column(name = "id", isId = true, autoGen = false)
    public int id;
}
