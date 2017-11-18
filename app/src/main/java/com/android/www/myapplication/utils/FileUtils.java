package com.android.www.myapplication.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class FileUtils {


    public static String getTempFile() {
        File fileTemp = new File(
                Environment.getExternalStorageDirectory(), "/blk/temp");
        if (!fileTemp.exists()) {
            fileTemp.mkdirs();
        }
        return fileTemp.getAbsolutePath();
    }

    public static boolean writeBitmapFile(String filename, Bitmap bitmap){
        File file = new File(filename);//设置文件名称
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void deleteAll() {
        File file = new File(getTempFile());
        if (file.exists() && file.isDirectory()) {
            String[] filenames = file.list();
            for (String filename : filenames) {
                if (new File(filename).isFile()) {
                    new File(filename).deleteOnExit();
                }
            }
        }
    }
}
