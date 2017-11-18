package com.android.www.myapplication.ben;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 数据库中始终保存最新一条测试记录，用于切换账号时显示
 * Created by gcy on 2017/08/04 0011.
 */
@Table(name = "latest_bodyinfo")
public class LatestBodyInfo implements Parcelable {
//    @Column(name = "id", isId = true, autoGen = true)
//    public int id;

    public static final Creator<LatestBodyInfo> CREATOR = new Creator<LatestBodyInfo>() {
        @Override
        public LatestBodyInfo createFromParcel(Parcel source) {
            return new LatestBodyInfo(source);
        }

        @Override
        public LatestBodyInfo[] newArray(int size) {
            return new LatestBodyInfo[size];
        }
    };

//    @Column(name = "birthOfDate")
//    public String birthOfDate;
@Column(name = "id", isId = true)
public String userid;//对应的用户id

    //    @Column(name = "height")
//    public int height;
//    @Column(name = "testTime")
//    public long testTime;
    @Column(name = "weight")
    public double weight;
    /**
     * 骨量 Bone Mass
     */
    @Column(name = "bm")
    public double BM;
    /**
     * 肌肉量 Rate of muscle
     */
    @Column(name = "rom")
    public double ROM;
    /**
     * 身体质量指数
     */
    @Column(name = "bmi")
    public double BMI;
    /**
     * 蛋白率 protein percentage
     */
    @Column(name = "pp")
    public double PP;
    /**
     * 水分率 Moisture
     */
    @Column(name = "moi")
    public double MOI;
    /**
     * 脂肪率 Body fat ratio
     */
    @Column(name = "bfr")
    public double BFR;
    /**
     * 皮下脂肪率 Subcutaneous fat rate
     */
    @Column(name = "sfr")
    public double SFR;
    /**
     * 骨骼肌率 Rate of skeletal muscle
     */
    @Column(name = "rosm")
    public double ROSM;
    /**
     * 内脏脂肪率
     */
    @Column(name = "uvi")
    public double UVI;
    /**
     * 基础代谢率 basal metabolic rate
     */
    @Column(name = "bmr")
    public double BMR;
    /**
     * 身体年龄 physical age
     */
    @Column(name = "pa")
    public int PA;
    @Column(name = "score")
    public double score;
    /**
     * 数据是否已经上传。
     */
//    @Column(name = "pa")
//    public boolean isUploaded;

//    @Column(name = "adc")
//    public int adc;

    @Column(name = "testTime2")
    public String testTime;//方便服务器的yyyy-MM-dd HH:mm:ss
    @Column(name = "isHand")
    public int isHand = 1;//手动添加 0为手动添加 1为测量
    @Column(name = "serverId")
    public int serverId;//记录在服务器中的id

//    public String getBirthOfDate() {
//        return birthOfDate;
//    }
//
//    public void setBirthOfDate(String birthOfDate) {
//        this.birthofdate = birthofdate;
//    }

//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }


    /**
     * 注意此处必须保留一个无参构造
     */
    public LatestBodyInfo() {
    }


    protected LatestBodyInfo(Parcel in) {
        this.userid = in.readString();
        this.weight = in.readDouble();
        this.BM = in.readDouble();
        this.ROM = in.readDouble();
        this.BMI = in.readDouble();
        this.PP = in.readDouble();
        this.MOI = in.readDouble();
        this.BFR = in.readDouble();
        this.SFR = in.readDouble();
        this.ROSM = in.readDouble();
        this.UVI = in.readDouble();
        this.BMR = in.readDouble();
        this.PA = in.readInt();
        this.score = in.readDouble();
        this.testTime = in.readString();
        this.isHand = in.readInt();
        this.serverId = in.readInt();
    }

    /**
     * 将测量的bmi相关数据赋值到本类中
     *
     * @param data
     */
    public void setData(BodyInfo data) {
        this.BFR = data.BFR;
        // this.isUploaded = false;
        this.BMI = data.BMI;
        this.BM = data.BM;
        this.ROM = data.ROM;
        this.PP = data.PP;
        this.MOI = data.MOI;
        this.SFR = data.SFR;
        this.ROSM = data.ROSM;
        this.UVI = data.UVI;
        this.BMR = data.BMR;
        this.PA = data.PA;

        this.weight = data.weight;
        this.userid = data.userid;
        this.testTime = data.testTime;

        this.score = data.score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userid);
        dest.writeDouble(this.weight);
        dest.writeDouble(this.BM);
        dest.writeDouble(this.ROM);
        dest.writeDouble(this.BMI);
        dest.writeDouble(this.PP);
        dest.writeDouble(this.MOI);
        dest.writeDouble(this.BFR);
        dest.writeDouble(this.SFR);
        dest.writeDouble(this.ROSM);
        dest.writeDouble(this.UVI);
        dest.writeDouble(this.BMR);
        dest.writeInt(this.PA);
        dest.writeDouble(this.score);
        dest.writeString(this.testTime);
        dest.writeInt(this.isHand);
        dest.writeInt(this.serverId);
    }
}
