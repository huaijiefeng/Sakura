package cn.ismartv.sakura.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.core.cache.CacheLoader;
import cn.ismartv.sakura.core.download.DownloadTask;
import cn.ismartv.sakura.provider.NodeCache;
import cn.ismartv.sakura.ui.adapter.NodeListAdapter;
import cn.ismartv.sakura.utils.StringUtilities;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fenghb on 14-6-25.
 */
public class NodeFragment extends Fragment implements AdapterView.OnItemClickListener,
        LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final String TAG = "NodeFragment";

    //view
    private GridView nodes;
    private Spinner citySpinner;
    private Spinner operatorSpinner;
    private Spinner listSpinner;
    private Button speedTestBtn;
    private TextView currentNode;

    public static Handler messageHandler;

    private List<HashMap<String, String>> list;
    private NodeListAdapter nodeListAdapter;

    private String[] selectionArgs;
    private String[] projection = null;

    private int cityPosition, operatorPosition, listPosition;


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
        //city
        citySpinner = (Spinner) view.findViewById(R.id.city_spinner);
        ArrayAdapter<CharSequence> citySpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.citys, android.R.layout.simple_spinner_item);
        citySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(citySpinnerAdapter);
        citySpinner.setOnItemSelectedListener(this);

        //operator
        operatorSpinner = (Spinner) view.findViewById(R.id.operator);
        ArrayAdapter<CharSequence> operatorSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.operators, android.R.layout.simple_spinner_item);
        operatorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operatorSpinner.setAdapter(operatorSpinnerAdapter);
        operatorSpinner.setOnItemSelectedListener(this);
        //list
        listSpinner = (Spinner) view.findViewById(R.id.list);
        ArrayAdapter<CharSequence> listSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.lists, android.R.layout.simple_spinner_item);
        listSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listSpinner.setAdapter(listSpinnerAdapter);
        listSpinner.setOnItemSelectedListener(this);

        //speed test button
        speedTestBtn = (Button) view.findViewById(R.id.speed_test_btn);
        speedTestBtn.setOnClickListener(this);

        //current node
        currentNode = (TextView) view.findViewById(R.id.current_node_text);
        Cursor cursor = getActivity().getContentResolver().query(NodeCache.CONTENT_URI,
                new String[]{NodeCache.NICK}, NodeCache.CHECKED + "=?", new String[]{"true"}, null);
        cursor.moveToFirst();
        currentNode.setText(cursor.getString(cursor.getColumnIndex(NodeCache.NICK)));
        cursor.close();

        nodeListAdapter.setCurrentNode(currentNode);

        nodes = (GridView) view.findViewById(R.id.node_list_view);
        nodes.setAdapter(nodeListAdapter);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


    }

    //----------------loader----------------
    @Override
    public Loader onCreateLoader(int flag, Bundle bundle) {
        String selection = NodeCache.AREA + "=? and " + NodeCache.OPERATOR + "=?";
        String selection2 = NodeCache.OPERATOR + "=?";
        CacheLoader cacheLoader = new CacheLoader(getActivity(), NodeCache.CONTENT_URI,
                null,
                null, null, null);
        switch (flag) {
            case 0:
                cacheLoader.setSelection(selection);
                cacheLoader.setSelectionArgs(selectionArgs);
                return cacheLoader;
            case 1:
                cacheLoader.setSelection(selection2);
                cacheLoader.setSelectionArgs(selectionArgs);
                return cacheLoader;
            case 2:
                cacheLoader.setSelection(null);
                cacheLoader.setSelectionArgs(null);
                return cacheLoader;
            default:
                break;
        }
        return null;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.speed_test_btn:
                speedTest();
                break;
            default:
                break;
        }
    }


    //On Item Selected Listener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Log.d(TAG, "view id is : " + view.getId() + "----" + adapterView.getId() + "-----" + id);
        String[] cities = getResources().getStringArray(R.array.citys);
        switch (adapterView.getId()) {
            case 2130968611:
                cityPosition = position;
                selectionArgs = new String[]{String.valueOf(StringUtilities.getAreaCodeByProvince(cities[cityPosition])),
                        String.valueOf(operatorPosition + 1)};
                getLoaderManager().restartLoader(0, null, this).forceLoad();
                break;
            case 2130968613:
                operatorPosition = position;
                selectionArgs = new String[]{String.valueOf(StringUtilities.getAreaCodeByProvince(cities[cityPosition])),
                        String.valueOf(operatorPosition + 1)};
                getLoaderManager().restartLoader(0, null, this).forceLoad();
                break;
            case 2130968615:
                listPosition = position;
                switch (listPosition) {
                    case 0:
                        operatorPosition = position;
                        selectionArgs = new String[]{String.valueOf(StringUtilities.getAreaCodeByProvince(cities[cityPosition])),
                                String.valueOf(operatorPosition + 1)};
                        getLoaderManager().destroyLoader(1);
                        getLoaderManager().destroyLoader(2);
                        getLoaderManager().restartLoader(0, null, this).forceLoad();
                        break;
                    case 1:
                        selectionArgs = new String[]{String.valueOf(operatorPosition + 1)};
                        getLoaderManager().destroyLoader(0);
                        getLoaderManager().destroyLoader(2);
                        getLoaderManager().restartLoader(1, null, this).forceLoad();
                        break;
                    case 2:
                        getLoaderManager().destroyLoader(0);
                        getLoaderManager().destroyLoader(1);
                        getLoaderManager().restartLoader(2, null, this).forceLoad();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    //------------------------------------------------------------


    private void speedTest() {
        DownloadTask downloadTask = new DownloadTask(getActivity(), nodeListAdapter.getCursor());
        if (!downloadTask.isAlive()) {
            downloadTask.start();
        }
    }

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
