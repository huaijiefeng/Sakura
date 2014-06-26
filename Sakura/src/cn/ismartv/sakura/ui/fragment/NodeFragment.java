package cn.ismartv.sakura.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.ui.adapter.NodeListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fenghb on 14-6-25.
 */
public class NodeFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String NODE_NAME = "node_name";
    public static final String NODE_CHECK = "node_check";
    public static final String NODE_SPEED = "node_speed";

    private GridView nodes;

    private List<HashMap<String, String>> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_node, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nodes = (GridView) view.findViewById(R.id.node_list_view);
        //test
        list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(NODE_NAME, "上海电信节点");
        map.put(NODE_SPEED, "80");
        map.put(NODE_CHECK, "false");
        for (int i = 0; i < 10; i++) {
            list.add(map);
        }
        nodes.setAdapter(new NodeListAdapter(getActivity(), list));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

    }

}
