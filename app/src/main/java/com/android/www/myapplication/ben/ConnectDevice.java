package com.android.www.myapplication.ben;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 将连接过的设备保存到数据库中
 * Created by gcy on 2017/9/21 0021.
 */
@Table(name = "connect_device")
public class ConnectDevice {

    @Column(isId = true, name = "id")
    public int id;

    @Column(name = "mac")
    public String mac;

    @Column(name = "name")
    public String name;

    @Column(name = "model")
    public String model;//设备型号

    /**
     * 注意必须保留无参构造
     */
    public ConnectDevice() {
    }

    public ConnectDevice(String mac, String name, String model) {
        this.mac = mac;
        this.name = name;
        this.model = model;
    }
}
