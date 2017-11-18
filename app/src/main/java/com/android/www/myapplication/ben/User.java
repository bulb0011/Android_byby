package com.android.www.myapplication.ben;

import com.google.gson.annotations.SerializedName;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;

import java.util.UUID;

/**
 * Created by Administrator on 2017/7/11 0011.
 */

@Table(name = "user")
public class User {

    public static final int MALE = 1;
    public static final int FEMALE = 2;

    @Column(name = "id", isId = true, autoGen = false)
    @SerializedName("id")
    public String id;

    @Column(name = "name")
    public String name; // 姓名

    @Column(name = "birthOfDate")
    @SerializedName("birthofdate")
    public String birthofdate;

    @Column(name = "height")
    public int height;

    @Column(name = "gender")
    public int gender;    // 性别 1男性 2 女性

    @Column(name = "age")
    public int age;

    @Column(name = "mobile")
    public String mobile; // 电话  如果为空，认为是主账号下的成员的信息。

    @Column(name = "imagePath")
    public String imagePath;  // 头像

    @Column(name = "relation")
    public String relation;  // 关系： 哥哥，弟弟，女儿，父亲等， 如果为空，认为是主账号本人。

    @Column(name = "index")
    public int index;//存储体脂称的序列号

    @Column(name = "current")
    public boolean current;//表示是否为当前选择的用户

    @Column(name = "token")
    public String token;

    @Column(name = "isUpload")
    public boolean isUpload;
    //
    private BodyInfo bodyInfo;

    public User() {
        id = UUID.randomUUID().toString();
    }

    public BodyInfo getBodyInfo(DbManager dbManager) {
        if (bodyInfo == null) {
            try {
                bodyInfo = dbManager.selector(BodyInfo.class).where("userid", "=", id).findFirst();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        return this.bodyInfo;
    }

    public BodyInfo getBodyInfo() {
        if (bodyInfo == null) {
            bodyInfo = new BodyInfo(id);
        }
        return this.bodyInfo;
    }

    public void setBodyInfo(BodyInfo info) {
        bodyInfo = info;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthOfDate='" + birthofdate + '\'' +
                ", height=" + height +
                ", gender=" + gender +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", relation='" + relation + '\'' +
                ", index=" + index +
                ", bodyInfo=" + bodyInfo +
                ", token = " + token +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id.equals(user.id);
    }
}
