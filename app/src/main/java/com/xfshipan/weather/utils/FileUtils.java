package com.xfshipan.weather.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/9/20.
 */
public class FileUtils {
    public static final String CACHE = "cache";
    public static final String ICON = "icon";
    public static final String ROOT = "weather";
    public static final String APK = "apk";

    /**
     * 获取apk缓存地址
     *
     * @return
     */
    public static File getAPKDir() {
        return getDir(APK);
    }

    /**
     * 获取图片缓存路径
     *
     * @return
     */
    public static File getIconDir() {
        return getDir(ICON);
    }

    /**
     * 获取缓存地址
     *
     * @return
     */
    public static File getCacheDir() {
        return getDir(CACHE);
    }


    //获取缓存路径
    public static File getDir(String cache) {
        //如果内存卡可用，存入内存卡，内存卡不可用，存入手机内存
        StringBuilder path = new StringBuilder();
        if (isSdAvailable()) {
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);// '/'
            path.append(ROOT);// '/mnt/sdcard/weather'
            path.append(File.separator);
            path.append(cache);// /mnt/sdcard/weather/miappstore
            path.append(File.separator);// '/'
        } else {
            File filesDir = UiUtils.getContext().getCacheDir();
            path.append(filesDir.getAbsolutePath());
            path.append(File.separator);// /data/data/包名/cache/
            path.append(cache);
            path.append(File.separator);// '/'
        }
        File file = new File(path.toString());
        if (!file.exists()) {
            //文件不存在，创建文件夹
            file.mkdirs();
        }
        return file;
    }

    //判断SD卡是否可用
    private static boolean isSdAvailable() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 复制数据库文件
     *
     * @param dbname
     */
    public static void copyDB(final String dbname) {
        ThreadManager.getInstance().createShortPool().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // getFilesDir() /data/data/files目录下
                            File file = new File(UiUtils.getContext().getFilesDir(), dbname);
                            if (file.exists() && file.length() > 0) {
                                Log.i("FileUtils", "数据库已经存在");
                                return;
                            }
                            InputStream is = UiUtils.getContext().getAssets().open(dbname);
                            FileOutputStream fos = UiUtils.getContext().openFileOutput(dbname, Context.MODE_PRIVATE);
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            while ((len = is.read(buffer)) != -1) {
                                fos.write(buffer, 0, len);
                            }
                            is.close();
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
