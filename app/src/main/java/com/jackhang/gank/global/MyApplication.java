package com.jackhang.gank.global;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

public class MyApplication extends Application {
    public static Context sContext;
    public static File cacheDirectory;
    public static File picDirectory;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        initDir();
    }

    /**
     * 初始缓存路径
     */
    private void initDir() {
        // 缓存路径
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDirectory = sContext.getExternalCacheDir();
            picDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "gank_pics");
        } else {
            cacheDirectory = sContext.getCacheDir();
            picDirectory = new File(sContext.getFilesDir(), "gank_pics");
        }
        mkDir(picDirectory);
    }

    public void mkDir(File dir) {
        if (dir.exists() && !dir.isDirectory())
            dir.delete();
        else if (!dir.exists())
            dir.mkdir();
    }
}
