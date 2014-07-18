package cn.ismartv.sakura.core.cache;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import cn.ismartv.sakura.data.Node;
import cn.ismartv.sakura.provider.NodeCache;
import cn.ismartv.sakura.utils.StringUtilities;

import java.util.ArrayList;

/**
 * Created by fenghb on 14-7-11.
 */
public class CacheManager {
    public static void updateNodeCache(Context context, String cdnId, String speed) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NodeCache.SPEED, speed);
        context.getContentResolver().update(NodeCache.CONTENT_URI, contentValues, NodeCache.CDN_ID, new String[]{cdnId});
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
            contentValues.put(NodeCache.AREA, StringUtilities.getAreaCodeByNode(node.getNick()));
            contentValues.put(NodeCache.OPERATOR, StringUtilities.getOperatorByNode(node.getNick()));
            contentValues.put(NodeCache.CHECKED, "false");
            context.getContentResolver().insert(NodeCache.CONTENT_URI, contentValues);
        }
    }

    public static void updateCheck(Context context, String cdnId, String checked) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NodeCache.CHECKED, checked);
        context.getContentResolver().update(NodeCache.CONTENT_URI, contentValues, NodeCache.CDN_ID, new String[]{cdnId});
    }

    public static void updateXML(Context context, String city, String operator) {
        SharedPreferences preferences = context.getSharedPreferences("sakura", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("city", city);
        editor.putString("operator", operator);
        editor.apply();
    }
}
