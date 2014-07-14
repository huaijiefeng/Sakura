package cn.ismartv.sakura.ui.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.core.cache.CacheLoader;
import cn.ismartv.sakura.provider.NodeCache;
import cn.ismartv.sakura.ui.adapter.NodeListAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fenghb on 14-6-25.
 */
public class NodeFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private GridView nodes;
    private List<HashMap<String, String>> list;


    public static Handler messageHandler;

    private NodeListAdapter nodeListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nodeListAdapter = new NodeListAdapter(getActivity(), null, true);
        initLoader();
    }

    //init loader
    private void initLoader() {
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_node, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nodes = (GridView) view.findViewById(R.id.node_list_view);
        nodes.setAdapter(nodeListAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

    }

    //loader
    @Override
    public Loader onCreateLoader(int id, Bundle bundle) {
        return new CacheLoader(getActivity(), NodeCache.CONTENT_URI,
                new String[]{NodeCache.ID, NodeCache.NICK, NodeCache.SPEED, NodeCache.CHECKED},
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        nodeListAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        nodeListAdapter.swapCursor(null);
    }

    //------------------------------------------------------------
    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                default:
                    break;
            }
        }
    }
}
