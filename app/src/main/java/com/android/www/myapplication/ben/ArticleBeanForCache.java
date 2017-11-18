package com.android.www.myapplication.ben;

import java.io.Serializable;

/**
 * 文章对应的vo
 * Created by tgcy on 2017/10/7 0007.
 */
public class ArticleBeanForCache implements Serializable {
    /*      "id": 64,
            "title": "地方",
            "author": 8,
            "creattime": "2017-10-08 11:00:14",
            "isup": "0",
            "isrecom": "0",
            "isshow": "0",
            "imgurl": "http://123.15.47.26:8080/uploadFile/http/filedownload?filePath=/zhiyinshou/2017/10/8/78df8de2e02042999ba1cccab9a88d63/1507431591868_57e02d3b
            -c869-461b-9e14-f1c400658f17.jpg",
            "type": "jfbk",
            "dataid": 116,
            "beiyong1": "2017-10-09 09:22:06",
            "beiyong2": null,
            "beiyong3": null,
            "textarea": "https://my.oschina.net/u/1434139/blog/224271",
            "realName": "changcc",
            "dataName": null,
            "serverPath": null*/


    public int id; // id

    public String title;

    public int author;

    public String creattime;

    public String isup;   // is set topping.

    public String isrecom;   // is recommend.

    public String isshow;      // is show

    public String imgurl;      // image url

    public String type;         // jianfeibaike,    shuxing,  lizhi, yundong
    public int dataid;         //
    public String beiyong1;     // update time

    public String linksUrl;     //
    public String beiyong3;     //
    public String textarea;     // is url or text content.
    public String realName;     // author name.
    public String dataName;     // 文章类型名称
    public String serverPath;
    public int viewCount;
    public int fengxiang;
    public int diancai;
    public int dianzan;
    public int shoucang;

    public ArticleBeanForCache() {
    }

    public ArticleBean genBean() {
        return new ArticleBean(id, title, author, creattime, isup, isrecom, isshow, imgurl, type, dataid,
                beiyong1, linksUrl, beiyong3, textarea, realName, dataName, serverPath, viewCount, fengxiang,
                diancai, dianzan, shoucang);
    }
}
