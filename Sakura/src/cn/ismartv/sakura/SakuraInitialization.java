package cn.ismartv.sakura;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import cn.ismartv.sakura.core.cache.CacheManager;
import cn.ismartv.sakura.core.httpclient.NetWorkUtilities;
import cn.ismartv.sakura.data.Node;

import java.util.ArrayList;

/**
 * Created by fenghb on 14-7-11.
 */
public class SakuraInitialization extends Thread {
    private static final String TAG = "SakuraInitialization";
    private Context context;

    public SakuraInitialization(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(TAG, "thread is running......");
        ArrayList<Node> nodes;
        if (NetWorkUtilities.nodeIsChanged()) {
            if (isFirstInstall()) {
                nodes = NetWorkUtilities.getNodeList();

                CacheManager.updateNodeCache(context, nodes);
            }
        } else {
            if (isFirstInstall()) {
                nodes = NetWorkUtilities.getNodeList();

                CacheManager.updateNodeCache(context, nodes);
            }
        }
    }

    private boolean isFirstInstall() {
        SharedPreferences preferences = context.getSharedPreferences("sakura", Context.MODE_PRIVATE);

        if (preferences.getBoolean("first_install", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("first_install", false);
            editor.apply();
            return true;
        }
        return false;

    }


}
