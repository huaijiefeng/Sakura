package cn.ismartv.sakura.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.core.httpclient.NetWorkUtilities;
import cn.ismartv.sakura.ui.adapter.NodeListAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fenghb on 14-6-25.
 */
public class NodeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView nodes;
    private List<HashMap<String, String>> list;

    public static final int TEST_COMPLETE = 0x0001;
    public static final int GET_NODE_LIST = 0x0002;
    public static final int GET_NODE_LIST_COMPLETE = 0x0003;
    public static final int CONNECTION_REFUSED = 0x0004;
    public static final int SPEEDTEST_COMPLETE = 0x0005;

    public static Handler messageHandler;

    private NodeListAdapter nodeListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageHandler = new MessageHandler();
        messageHandler.sendEmptyMessage(GET_NODE_LIST);     //get node list from server
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_node, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nodes = (GridView) view.findViewById(R.id.node_list_view);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
    }

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TEST_COMPLETE:
                    break;
                case GET_NODE_LIST:
                    break;
                case GET_NODE_LIST_COMPLETE:
                    break;
                case CONNECTION_REFUSED:
                    Toast.makeText(getActivity(), R.string.connect_refused, Toast.LENGTH_LONG).show();
                    break;
                case SPEEDTEST_COMPLETE:
                    break;
                default:
                    break;
            }
        }
    }
}
