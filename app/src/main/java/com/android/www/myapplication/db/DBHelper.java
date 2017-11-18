package com.android.www.myapplication.db;

import android.content.Context;

import com.android.www.myapplication.ben.BodyInfo;
import com.android.www.myapplication.ben.ConnectDevice;
import com.android.www.myapplication.ben.LatestBodyInfo;

import org.xutils.DbManager;
import org.xutils.db.DbManagerImpl;
import org.xutils.ex.DbException;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class DBHelper {

    private static DbManager db;
    private static DbManager.DaoConfig daoConfig;

    public static DbManager getDbManager(Context context) {
        db = DbManagerImpl.getInstance(getConfig(),
                context.getApplicationContext());
        return db;
    }

    public static DbManager.DaoConfig getConfig() {
        if (daoConfig == null) {
            daoConfig = new DbManager.DaoConfig()
                    .setDbName("localdata.db")
                    // 不设置dbDir时, 默认存储在app的私有目录.
                    .setDbVersion(5)
                    .setDbOpenListener(new DbManager.DbOpenListener() {
                        @Override
                        public void onDbOpened(DbManager db) {
                            // 开启WAL, 对写入加速提升巨大
                            db.getDatabase().enableWriteAheadLogging();
                        }
                    })
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            if (oldVersion < 2) {
                                try {
                                    db.addColumn(BodyInfo.class, "score");
                                } catch (DbException e) {
                                 //   LogUtils.e(e.getMessage());
                                }
                                try {
                                    db.addColumn(LatestBodyInfo.class, "score");
                                } catch (DbException e) {

                                }

                                try {
                                    db.addColumn(LatestBodyInfo.class, "testTime2");
                                } catch (DbException e) {

                                }
                                try {
                                    db.addColumn(LatestBodyInfo.class, "isHand");
                                } catch (DbException e) {

                                }
                                try {
                                    db.addColumn(LatestBodyInfo.class, "serverId");
                                } catch (DbException e) {

                                }
                                try {
                                    db.addColumn(BodyInfo.class, "isHand");
                                } catch (DbException e) {

                                }
                                try {
                                    db.addColumn(BodyInfo.class, "serverId");
                                } catch (DbException e) {

                                }
                            }

                            if (oldVersion == 2) {
                                try {
                                    db.addColumn(LatestBodyInfo.class, "testTime2");
                                } catch (DbException e) {

                                }

                                try {
                                    db.addColumn(LatestBodyInfo.class, "isHand");
                                } catch (DbException e) {

                                }

                                try {
                                    db.addColumn(LatestBodyInfo.class, "serverId");
                                } catch (DbException e) {

                                }

                                try {
                                    db.addColumn(BodyInfo.class, "isHand");
                                } catch (DbException e) {

                                }
                                try {
                                    db.addColumn(BodyInfo.class, "serverId");
                                } catch (DbException e) {

                                }
                            }
                            if (oldVersion == 3) {
                                try {
                                    db.addColumn(LatestBodyInfo.class, "isHand");
                                } catch (DbException e) {

                                }
                                try {
                                    db.addColumn(LatestBodyInfo.class, "serverId");
                                } catch (DbException e) {

                                }

                                try {
                                    db.addColumn(BodyInfo.class, "isHand");
                                } catch (DbException e) {

                                }
                                try {
                                    db.addColumn(BodyInfo.class, "serverId");
                                } catch (DbException e) {

                                }
                            }

                            if (oldVersion < 5) {
                                try {
                                    db.addColumn(ConnectDevice.class, "model");
                                } catch (DbException e) {

                                }
                            }
                            // if (oldVersion == 0 && newVersion == 1) {

                            // }
                        }
                    })
            ;

            /**
             * 由于AS3.0升级，现在可以很方便查看内置存储的数据库，以下代码注释掉
             */
//            if (BuildConfig.DEBUG) {
//                daoConfig.setDbDir(new File(Environment.getExternalStorageDirectory().getAbsolutePath()));
//            }
        }
        return daoConfig;
    }
}
