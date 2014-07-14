package cn.ismartv.sakura.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.provider.NodeCache;

/**
 * Created by fenghb on 14-6-24.
 */
public class NodeListAdapter extends CursorAdapter {
    public NodeListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public NodeListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_node_list, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nodeName = (TextView) view.findViewById(R.id.node_name);
        ProgressBar nodeSpeed = (ProgressBar) view.findViewById(R.id.node_speed);
        RadioButton nodeCheck = (RadioButton) view.findViewById(R.id.node_check);
        String node = cursor.getString(cursor.getColumnIndex(NodeCache.NICK));
        nodeName.setText(node);
    }
}
