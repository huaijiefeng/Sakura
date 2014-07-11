package cn.ismartv.sakura;

import android.content.Context;
import cn.ismartv.sakura.core.cache.CacheManager;
import cn.ismartv.sakura.core.httpclient.NetWorkUtilities;
import cn.ismartv.sakura.data.Node;

import java.util.ArrayList;

/**
 * Created by fenghb on 14-7-11.
 */
public class SakuraInitialization extends Thread {
    private Context context;

    public SakuraInitialization(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        if (NetWorkUtilities.nodeIsChanged()) {
            ArrayList<Node> nodes = NetWorkUtilities.getNodeList();
            CacheManager.updateNodeCache(context, nodes);
        } else {
            ArrayList<Node> nodes = NetWorkUtilities.getNodeList();
            CacheManager.updateNodeCache(context, nodes);
        }
    }
}
