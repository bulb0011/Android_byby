package com.android.www.myapplication.ben;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文章对应的vo
 * Created by tgcy on 2017/10/7 0007.
 */

public class ArticleBean implements Parcelable{
    /*      "id": 64,
            "title": "地方",
            "author": 8,
            "creattime": "2017-10-08 11:00:14",
            "isup": "0",
            "isrecom": "0",
            "isshow": "0",
            "imgurl": "http://123.15.47.26:8080/uploadFile/http/filedownload?filePath=/zhiyinshou/2017/10/8/78df8de2e02042999ba1cccab9a88d63/1507431591868_57e02d3b-c869-461b-9e14-f1c400658f17.jpg",
            "type": "jfbk",
            "dataid": 116,
            "beiyong1": "2017-10-09 09:22:06",
            "beiyong2": null,
            "beiyong3": null,
            "textarea": "https://my.oschina.net/u/1434139/blog/224271",
            "realName": "changcc",
            "dataName": null,
            "serverPath": null*/

    public static final Creator<ArticleBean> CREATOR = new Creator<ArticleBean>() {
        @Override
        public ArticleBean createFromParcel(Parcel in) {
            return new ArticleBean(in);
        }

        @Override
        public ArticleBean[] newArray(int size) {
            return new ArticleBean[size];
        }
    };
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

    public ArticleBean() {
    }

    public ArticleBean(int id, String title, int author, String creattime, String isup, String isrecom, String isshow, String imgurl, String type, int dataid, String
            beiyong1, String linksUrl, String beiyong3, String textarea, String realName, String dataName, String serverPath, int viewCount, int fengxiang, int
                               diancai, int dianzan, int shoucang) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.creattime = creattime;
        this.isup = isup;
        this.isrecom = isrecom;
        this.isshow = isshow;
        this.imgurl = imgurl;
        this.type = type;
        this.dataid = dataid;
        this.beiyong1 = beiyong1;
        this.linksUrl = linksUrl;
        this.beiyong3 = beiyong3;
        this.textarea = textarea;
        this.realName = realName;
        this.dataName = dataName;
        this.serverPath = serverPath;
        this.viewCount = viewCount;
        this.fengxiang = fengxiang;
        this.diancai = diancai;
        this.dianzan = dianzan;
        this.shoucang = shoucang;
    }

    protected ArticleBean(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readInt();
        creattime = in.readString();
        isup = in.readString();
        isrecom = in.readString();
        isshow = in.readString();
        imgurl = in.readString();
        type = in.readString();
        dataid = in.readInt();
        beiyong1 = in.readString();
        linksUrl = in.readString();
        beiyong3 = in.readString();
        textarea = in.readString();
        realName = in.readString();
        dataName = in.readString();
        serverPath = in.readString();
        viewCount = in.readInt();
        fengxiang = in.readInt();
        diancai = in.readInt();
        dianzan = in.readInt();
        shoucang = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(author);
        parcel.writeString(creattime);
        parcel.writeString(isup);
        parcel.writeString(isrecom);
        parcel.writeString(isshow);
        parcel.writeString(imgurl);
        parcel.writeString(type);
        parcel.writeInt(dataid);
        parcel.writeString(beiyong1);
        parcel.writeString(linksUrl);
        parcel.writeString(beiyong3);
        parcel.writeString(textarea);
        parcel.writeString(realName);
        parcel.writeString(dataName);
        parcel.writeString(serverPath);
        parcel.writeInt(viewCount);
        parcel.writeInt(fengxiang);
        parcel.writeInt(diancai);
        parcel.writeInt(dianzan);
        parcel.writeInt(shoucang);
    }
}
