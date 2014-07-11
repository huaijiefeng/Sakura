package cn.ismartv.sakura.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.data.Nodes;
import cn.ismartv.sakura.ui.fragment.NodeFragment;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fenghb on 14-6-24.
 */
public class NodeListAdapter extends BaseAdapter {
    private Context mContext;

    public void setNodeList(Nodes nodeList) {
        this.nodeList = nodeList;
    }

    private Nodes nodeList;

    public NodeListAdapter(Context context, Nodes nodeList) {
        this.mContext = context;
        this.nodeList = nodeList;
    }

    @Override
    public int getCount() {
        return nodeList.getCdn_list().size();
    }

    @Override
    public Object getItem(int position) {
        return nodeList.getCdn_list().get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_node_list, null);
            convertView.setFocusable(false);
            holder.nodeCheck = (RadioButton) convertView.findViewById(R.id.node_check);
            holder.nodeSpeed = (ProgressBar) convertView.findViewById(R.id.node_speed);
            holder.nodeName = (TextView) convertView.findViewById(R.id.node_name);
            convertView.setTag(holder);
        }
        Nodes.Node node = nodeList.getCdn_list().get(position);
        holder.nodeName.setText(node.getName());
        return convertView;
    }

    private class ViewHolder {
        private RadioButton nodeCheck;
        private ProgressBar nodeSpeed;
        private TextView nodeName;
    }
}
