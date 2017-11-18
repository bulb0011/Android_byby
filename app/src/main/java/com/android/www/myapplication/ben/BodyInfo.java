package com.android.www.myapplication.ben;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/11 0011.
 * gcy 2017-09-01新增得分字段，相应数据库已做升级
 */
@Table(name = "bodyinfo")
public class BodyInfo implements Serializable, Comparable {
    @Column(name = "id", isId = true, autoGen = true)
    public int id;

    @Column(name = "userid")
    @SerializedName("userId")
    public String userid;//对应的用户id

    @Column(name = "weight")
    public double weight;

    /**
     * 此变量存在的意义在于上传，由于体重数据和adc不是同事传过来的，需要等待
     */
    @Column(name = "testTime")
    public long testTime2;

    @Column(name = "testTime2")
    public String testTime;//方便服务器的yyyy-MM-dd HH:mm:ss

    @Column(name = "date")
    public String date;//方便本地查询的yyyy-MM-dd
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
    /**
     * 数据是否已经上传。
     */
    @Column(name = "isUploaded")
    public boolean isUploaded = true;

    @Column(name = "adc")
    public int adc;

    @Column(name = "score")
    @SerializedName("evaluation")
    public double score;

    @SerializedName("flag")
    @Column(name = "isHand")
    public int isHand = 0;//手动添加 0为测量 1为手动

    @SerializedName("dataid")
    @Column(name = "serverId")
    public int serverId;//记录在服务器中的id

    public BodyInfo(String userid) {
        this.userid = userid;
    }

    /**
     * 注意此处必须保留一个无参构造
     */
    public BodyInfo() {
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }

    /**
     * 将所有变量恢复默认值
     */
    public void clear() {
        id = 0;
        userid = null;
        testTime = "";
        //testTime2 = 0;
        score = BM = ROM = BMI = PP = MOI = BFR = SFR = ROSM = UVI = BMR = PA = adc = 0;
        isUploaded = false;
    }

    /**
     * 将测量的bmi相关数据赋值到本类中
     *
     * @param data
     */
//    public void setData(aicare.net.cn.entity.BodyFatData data) {
//        this.BFR = data.getBFR();
//        this.isUploaded = false;
//        this.BMI = data.getBMI();
//        this.BM = data.getBM();
//        this.ROM = data.getROM();
//        this.PP = data.getPP();
//        this.MOI = data.getMOI();
//        this.SFR = data.getSFR();
//        this.ROSM = data.getROSM();
//        this.UVI = data.getUVI();
//        this.BMR = data.getBMR();
//        this.PA = data.getPA();
//    }

    /**
     * 将测量的bmi相关数据赋值到本类中
     *
     * @param data
     */
////    public void setData(QNData data) {
////        clear();
////
////        this.userid = data.getUserId();
////        this.weight = data.getWeight();
////
////        Date date = data.getCreateTime();
////        this.testTime2 = date.getTime();
////
////        Calendar calendar = Calendar.getInstance();
////        calendar.setTime(date);
////
////        int year = calendar.get(Calendar.YEAR);
////        int month = calendar.get(Calendar.MONTH) + 1;
////        int day = calendar.get(Calendar.DAY_OF_MONTH);
////        int hour = calendar.get(Calendar.HOUR_OF_DAY);
////        int minute = calendar.get(Calendar.MINUTE);
////        int second = calendar.get(Calendar.SECOND);
////
////        this.date = year + "-" + (month > 9 ? month : "0" + month) + "-" + (day > 9 ? day : "0" + day);
////
////        this.testTime = this.date
////                + " " + (hour > 9 ? hour : "0" + hour) +
////                ":" + (minute > 9 ? minute : "0" + minute) +
////                ":" + (second > 9 ? second : "0" + second);
////
////        this.isUploaded = false;
////
////        List<QNItemData> datas = data.getAll();
////        for (QNItemData itemData : datas) {
////            double val = format(itemData.value);
////
////            switch (itemData.type) {
////                case QNData.TYPE_BODYFAT:
////                    this.BFR = val;
////                    break;
////                case QNData.TYPE_BMI:
////                    this.BMI = val;
////                    break;
////                case QNData.TYPE_BONE:
////                    this.BM = val;
////                    break;
////                case QNData.TYPE_SINEW:
////                    this.ROM = val;
////                    break;
////                case QNData.TYPE_PROTEIN:
////                    this.PP = val;
////                    break;
////                case QNData.TYPE_WATER:
////                    this.MOI = val;
////                    break;
////                case QNData.TYPE_SKELETAL_MUSCLE:
////                    this.ROSM = val;
////                    break;
////                case QNData.TYPE_SUBFAT:
////                    this.SFR = val;
////                    break;
////                case QNData.TYPE_VISFAT:
////                    this.UVI = val;
////                    break;
////                case QNData.TYPE_BMR:
////                    this.BMR = val;
////                    break;
////                case QNData.TYPE_BODYAGE:
////                    this.PA = (int) itemData.value;
////                    break;
////                case QNData.TYPE_SCORE:
////                    this.score = itemData.value;
////                    break;
//////                case QNData.TYPE_BONE:
//////                    this.BM = itemData.value;
//////                    break;
////            }
//        }
//
//
//        //this.BFR = data.getFloatValue(QNData.TYPE_BODYFAT);
//        //this.BMI = data.getFloatValue(QNData.TYPE_BMI);
//        // this.BM = data.getFloatValue(QNData.TYPE_BONE);
//        //this.ROM = data.getFloatValue(QNData.TYPE_SINEW);
//        // this.PP = data.getFloatValue(QNData.TYPE_PROTEIN);
//        // this.MOI = data.getFloatValue(QNData.TYPE_WATER);
//        //this.SFR = data.getFloatValue(QNData.TYPE_SUBFAT);
//        // this.ROSM = data.getFloatValue(QNData.TYPE_SKELETAL_MUSCLE);
//        // this.UVI = data.getFloatValue(QNData.TYPE_VISFAT);
//        // this.BMR = data.getFloatValue(QNData.TYPE_BMR);
//        //this.PA = data.getIntValue(QNData.TYPE_BODYAGE);
//        // this.adc = data.getIntValue(QNData.TYPE_RESISTANCE);
//    }
//
//    private double format(float v) {
//        java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");
//        double newVal = Double.parseDouble(df.format(v));
//        // LogUtils.e("format weight:" + newVal);
//        return newVal;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof BodyInfo)) return false;
//
//        BodyInfo bodyInfo = (BodyInfo) o;
//
//        return testTime.equals(bodyInfo.testTime);
//    }
//
//
//    @Override
//    public int compareTo(@NonNull Object o) {
//        BodyInfo bodyInfo = (BodyInfo) o;
//        return this.testTime.compareTo(bodyInfo.testTime);
//    }
//
//    public void date() {
//        this.date = this.testTime.split(" ")[0];
//    }
//
//    @Override
//    public String toString() {
//        return "BodyInfo{" +
//                "id=" + id +
//                ", userid='" + userid + '\'' +
//                ", weight=" + weight +
//                ", testTime2=" + testTime2 +
//                ", testTime='" + testTime + '\'' +
//                ", date='" + date + '\'' +
//                ", BM=" + BM +
//                ", ROM=" + ROM +
//                ", BMI=" + BMI +
//                ", PP=" + PP +
//                ", MOI=" + MOI +
//                ", BFR=" + BFR +
//                ", SFR=" + SFR +
//                ", ROSM=" + ROSM +
//                ", UVI=" + UVI +
//                ", BMR=" + BMR +
//                ", PA=" + PA +
//                ", isUploaded=" + isUploaded +
//                ", adc=" + adc +
//                '}';
//    }

}
