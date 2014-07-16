package cn.ismartv.sakura.utils;

import android.database.Cursor;
import cn.ismartv.sakura.data.Node;
import cn.ismartv.sakura.provider.NodeCache;

import java.util.ArrayList;

/**
 * Created by fenghb on 14-7-11.
 */
public class Utilities {

    public static ArrayList<Node> cursorToList(Cursor cursor) {
        ArrayList<Node> list = new ArrayList<Node>();
        while (cursor.moveToNext()) {
            Node node = new Node();
            node.setName(cursor.getString(cursor.getColumnIndex(NodeCache.NODE)));
            node.setCdnID(cursor.getString(cursor.getColumnIndex(NodeCache.CDN_ID)));
            node.setUrl(cursor.getString(cursor.getColumnIndex(NodeCache.IP)));
            node.setFlag(cursor.getString(cursor.getColumnIndex(NodeCache.FLAG)));
            node.setRoute_trace(cursor.getString(cursor.getColumnIndex(NodeCache.ROUTE_TRACE)));
            node.setSpeed(cursor.getString(cursor.getColumnIndex(NodeCache.SPEED)));


            list.add(node);
        }
        cursor.close();
        return list;
    }
}
