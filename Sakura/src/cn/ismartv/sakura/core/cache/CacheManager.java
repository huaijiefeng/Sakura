package cn.ismartv.sakura.core.cache;


import android.content.Context;
import cn.ismartv.sakura.data.Nodes;
import cn.ismartv.sakura.provider.node.Node;

/**
 * Created by fenghb on 14-7-11.
 */
public class CacheManager {
    public static void updateNodeCache(Context context, Nodes nodes) {
        for (Nodes.Node node : nodes.getCdn_list()) {
//            context.getContentResolver().insert(Node.Cache.CONTENT_URI, );

        }
    }
}
