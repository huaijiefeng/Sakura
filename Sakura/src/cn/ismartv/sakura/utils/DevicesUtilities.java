package cn.ismartv.sakura.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by fenghb on 14-7-2.
 */
public class DevicesUtilities {
    private static final String TAG = "DevicesUtilities";

    /**
     * if exist sdcard on android devices
     *
     * @return
     */
    public static boolean isExistSDCard() {


        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(TAG, "SDCard is available");
            return true;
        } else {
            Log.d(TAG, "SDCard is not available");
            return false;
        }
    }

    public static String getAppCacheDirectory() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Sakura" +
                File.separator + "cache");
        if (!file.exists() && !file.isDirectory())
            file.mkdirs();
        return file.getAbsolutePath();
    }
}
