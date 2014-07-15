package cn.ismartv.sakura;

import android.app.Application;
import android.util.Log;
import cn.ismartv.sakura.core.httpclient.NetWorkUtilities;
import cn.ismartv.sakura.utils.DevicesUtilities;

/**
 * Created by fenghb on 14-7-7.
 */
public class SakuraApplication extends Application {
    private static final String TAG = "SakuraApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "on create ......");
        Log.d(TAG, DevicesUtilities.getSNCode());
        NetWorkUtilities.bindCdn("5");

        NetWorkUtilities.getBindcdn();
//        SakuraInitialization initialization = new SakuraInitialization(getApplicationContext());
//        initialization.start();
    }
}
