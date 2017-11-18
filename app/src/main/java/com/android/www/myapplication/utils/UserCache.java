package com.android.www.myapplication.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.android.www.myapplication.ben.HealthManager;
import com.android.www.myapplication.ben.User;
import com.android.www.myapplication.db.DBHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.xutils.ex.DbException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gcy on 2017/8/03 0011.
 */

public class UserCache {


    private static User currentUser;
    private static User mainUser;
    private static List<User> userList;

    private static HealthManager healthManager;

    public static HealthManager getHM(Context app) {
        if (healthManager == null) {
            try {
                healthManager = DBHelper.getDbManager(app).findAll(HealthManager.class).get(0);
            } catch (Exception e) {
            }
        }

        return healthManager;
    }

    public static HealthManager setHM(Context app, HealthManager hm) {
        if (hm != null) {
            healthManager = hm;
            try {
                DBHelper.getDbManager(app).delete(HealthManager.class);
            } catch (Exception e) {
            }

            try {
                DBHelper.getDbManager(app).save(hm);
            } catch (Exception e) {

            }
        }
        return healthManager;
    }


    public static User getCurrentUser(Context app) {
        if (currentUser == null) {
            try {
                currentUser = DBHelper.getDbManager(app).selector(User.class).where("current", "=", "1").findFirst();
            } catch (Exception e) {
            }
        }
        if (currentUser == null) {
            User mainUser = getMainUser(app);
            if (mainUser != null) {
                currentUser = mainUser;
                setCurrentUser(app, mainUser);
            }
        }
        return currentUser;
    }

    public static void setCurrentUser(Context app, User user) {
        User lastuser = getCurrentUser(app);
        if (lastuser == null) {
            user.current = true;
            currentUser = user;
            try {
                DBHelper.getDbManager(app).saveOrUpdate(user);
            } catch (Exception e) {
            }
        } else {
            if (!lastuser.id.equals(user.id)) {
                lastuser.current = false;
                try {
                    DBHelper.getDbManager(app).saveOrUpdate(lastuser);
                } catch (Exception e) {
                }
                currentUser = user;
                user.current = true;
                try {
                    DBHelper.getDbManager(app).saveOrUpdate(user);
                } catch (Exception e) {
                }
            } else {
                currentUser = user;
                user.current = true;
                try {
                    DBHelper.getDbManager(app).saveOrUpdate(user);
                } catch (DbException e) {
                }
            }
        }
    }

    public static List<User> getUserList(Context app) {
        if (userList == null) {
            try {
                userList = DBHelper.getDbManager(app).selector(User.class)
                        .orderBy("index")
                        .findAll();
//                User mainuser = getMainUser(app);
//
//                userList.remove(mainuser);
//                userList.add(0, mainuser);
            } catch (Exception e) {
            }
        }

        return userList;
    }

    /**
     * 用户新添加了家庭成员，此处清空list，重新查询
     */
    public static void clearUserList() {
        userList = null;
    }

    public static void clear() {
        clearUserList();
        currentUser = mainUser = null;
    }

    public static void setUserList(Context app, List<User> userList) {
        if (UserCache.userList != null) {
            try {
                DBHelper.getDbManager(app).delete(UserCache.userList);
            } catch (Exception e) {
            }
        }
        UserCache.userList = userList;
        try {
            DBHelper.getDbManager(app).save(userList);
        } catch (Exception e) {
        }
        for (User user: userList) {
            cacheAvaterBitmap(app, user);
        }
    }

    public static User getMainUser(Context app) {
        if (mainUser == null) {
         //   LogUtils.i("get main user form db.");
            try {
                mainUser = DBHelper.getDbManager(app).selector(User.class).where("mobile", "!=", null).findFirst();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

        return mainUser;
    }

    public static void setMainUser(Context c, User user) {
        mainUser = user;
        cacheAvaterBitmap(c, user);
//        try {
//            DBHelper.getDbManager(c).saveOrUpdate(user);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
    }


    public static void saveUser(Application app, User user) {
        try {
            DBHelper.getDbManager(app).saveOrUpdate(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void  saveUser(Context context, User cacheUser){
        try {
            DBHelper.getDbManager(context).saveOrUpdate(cacheUser);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private static void cacheAvaterBitmap(final Context context, final User cacheUser) {
        SimpleTarget target = new SimpleTarget<Bitmap>(100, 100) {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
              //  LogUtils.i("resource = " + resource);
                String filename = getFileNameByid(cacheUser.id);
                FileUtils.writeBitmapFile(filename, resource);
            }
        };
        Glide.with(context)
                .asBitmap().load(cacheUser.imagePath).into(target);
    }

    public static String getCacheImagePath(String uerid){
        String filename = getFileNameByid(uerid);
        File file = new File(filename);
        if ( file.exists()) {
            return filename;
        }
        return null;
    }

    private static String getFileNameByid(String id) {
        String filename = FileUtils.getTempFile() + "/" + id + ".jpg";
        return filename;
    }

    public static void remove(Application app, User user) {
        UserCache.userList.remove(user);
        FileUtils.deleteAll();

        try {
            DBHelper.getDbManager(app).deleteById(User.class, user.id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        int len = UserCache.userList.size();
        for (int i = 0; i < len; ++i) {
            UserCache.userList.get(i).index = i;
        }
        try {
            DBHelper.getDbManager(app).saveOrUpdate(UserCache.userList);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static void removeDrawerUser(Application app) {
        try {
            List<User> list = DBHelper.getDbManager(app).selector(User.class).where("mobile", "=", null)
                    // .and("isUpload", "=", 1)
                    .findAll();

            DBHelper.getDbManager(app).delete(list);

            userList = null;
        } catch (Exception e) {
        }
    }

    public static List<User> getLeftList(Context app) {
        List<User> leftList = new ArrayList<>();
        List<User> userlist = getUserList(app);
        User current = getCurrentUser(app);
        for (User user : userlist) {
            if (!user.id.equals(current.id))
                leftList.add(user);
        }
        return leftList;
    }

    /**
     * 获取用户在列表中的位置
     *
     * @param user
     * @param app
     * @return
     */
    public static int getUserPos(User user, Context app) {
        List<User> userlist = getUserList(app);
        return userlist.indexOf(user);
    }

//    /**
//     * 获取未上传的用户信息
//     *
//     * @param app
//     * @return
//     */
//    public static List<User> getLeftUser(BLKApp app) {
//        try {
//            List<User> list = DBHelper.getDbManager(app).selector(User.class).where("isUpload", "=", 0).findAll();
//            return list;
//        } catch (DbException e) {
//        }
//        return null;
//    }
}
