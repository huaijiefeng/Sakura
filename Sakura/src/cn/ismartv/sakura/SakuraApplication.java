package cn.ismartv.sakura;

import android.app.Application;
import android.util.Log;

/**
 * Created by fenghb on 14-7-7.
 */
public class SakuraApplication extends Application {
    private static final String TAG = "SakuraApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "on create ......");

        SakuraInitialization initialization = new SakuraInitialization(getApplicationContext());
        initialization.start();
    }
}
