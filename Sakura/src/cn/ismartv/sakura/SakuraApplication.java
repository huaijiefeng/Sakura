package cn.ismartv.sakura;

import android.app.Application;
import android.content.Context;

/**
 * Created by fenghb on 14-7-7.
 */
public class SakuraApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SakuraInitialization initialization = new SakuraInitialization(getApplicationContext());
        initialization.start();
    }
}
