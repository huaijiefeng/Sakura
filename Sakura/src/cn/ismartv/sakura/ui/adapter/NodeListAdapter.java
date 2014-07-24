package cn.ismartv.sakura.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.core.httpclient.NetWorkUtilities;
import cn.ismartv.sakura.data.Node;
import cn.ismartv.sakura.provider.NodeCache;
import cn.ismartv.sakura.ui.widget.progressbar.SmoothProgressBar;

import java.util.HashMap;

/**
 * Created by fenghb on 14-6-24.
 */
public class NodeListAdapter extends CursorAdapter implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = NodeListAdapter.class.getSimpleName();
    private Context context;
    private TextView textView;


    public NodeListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        this.context = context;
    }

    public NodeListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.context = context;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_node_list, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d(TAG, "size is : " + cursor.getCount());
        TextView nodeName = (TextView) view.findViewById(R.id.node_name);
        ProgressBar nodeSpeed = (ProgressBar) view.findViewById(R.id.node_speed);
        RadioButton nodeCheck = (RadioButton) view.findViewById(R.id.node_check);
        String node = cursor.getString(cursor.getColumnIndex(NodeCache.NICK));
        int speed = cursor.getInt(cursor.getColumnIndex(NodeCache.SPEED));
        String checked = cursor.getString(cursor.getColumnIndex(NodeCache.CHECKED));
        String cdnId = cursor.getString(cursor.getColumnIndex(NodeCache.CDN_ID));
        nodeName.setText(node);
        nodeSpeed.setProgress((int) (speed / 20.48));
        nodeCheck.setChecked(checked.equals("true") ? true : false);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("cdn_id", cdnId);
        map.put("node", node);
        nodeCheck.setTag(map);
        if (checked.equals("true") ? true : false)
            textView.setText(node);
        nodeCheck.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        HashMap<String, String> map = (HashMap<String, String>) compoundButton.getTag();
        NetWorkUtilities.bindCdn(context, map.get("cdn_id"));
    }

    public void setCurrentNode(TextView textView) {
        this.textView = textView;
    }
}
