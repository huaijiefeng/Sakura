package cn.ismartv.sakura.core.cache;


import android.content.ContentValues;
import android.content.Context;
import cn.ismartv.sakura.data.Node;
import cn.ismartv.sakura.provider.NodeCache;

import java.util.ArrayList;

/**
 * Created by fenghb on 14-7-11.
 */
public class CacheManager {
    public static void updateNodeCache(Context context, Node nodes) {

    }

    public static void updateNodeCache(Context context, ArrayList<Node> nodes) {
        ContentValues contentValues = new ContentValues();
        for (Node node : nodes) {
            contentValues.put(NodeCache.NODE, node.getName());
            context.getContentResolver().insert(NodeCache.CONTENT_URI, contentValues);
        }
    }
}
