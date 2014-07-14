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
    public static void updateNodeCache(Context context, Node node) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NodeCache.NODE, node.getName());
        contentValues.put(NodeCache.CDN_ID, node.getCdnID());
        contentValues.put(NodeCache.NICK, node.getNick());
        contentValues.put(NodeCache.FLAG, node.getFlag());
        contentValues.put(NodeCache.IP, node.getUrl());
        contentValues.put(NodeCache.URL, node.getTestFile());
        contentValues.put(NodeCache.ROUTE_TRACE, node.getRoute_trace());
        contentValues.put(NodeCache.SPEED, node.getSpeed());
        contentValues.put(NodeCache.UPDATE_TIME, System.currentTimeMillis());
        contentValues.put(NodeCache.CHECKED, "true");
        context.getContentResolver().update(NodeCache.CONTENT_URI, contentValues, NodeCache.CDN_ID, new String[]{node.getCdnID()});
    }

    public static void updateNodeCache(Context context, ArrayList<Node> nodes) {
        ContentValues contentValues = new ContentValues();
        for (Node node : nodes) {
            contentValues.put(NodeCache.NODE, node.getName());
            contentValues.put(NodeCache.CDN_ID, node.getCdnID());
            contentValues.put(NodeCache.NICK, node.getNick());
            contentValues.put(NodeCache.FLAG, node.getFlag());
            contentValues.put(NodeCache.IP, node.getUrl());
            contentValues.put(NodeCache.URL, node.getTestFile());
            contentValues.put(NodeCache.ROUTE_TRACE, node.getRoute_trace());
            contentValues.put(NodeCache.SPEED, node.getSpeed());
            contentValues.put(NodeCache.UPDATE_TIME, System.currentTimeMillis());
            contentValues.put(NodeCache.CHECKED, "true");
            context.getContentResolver().insert(NodeCache.CONTENT_URI, contentValues);
        }
    }
}
