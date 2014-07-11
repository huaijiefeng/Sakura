package cn.ismartv.sakura.core.httpclient;

import android.os.Message;
import android.util.Log;
import cn.ismartv.sakura.core.cache.CacheManager;
import cn.ismartv.sakura.core.download.HttpDownload;
import cn.ismartv.sakura.data.Nodes;
import cn.ismartv.sakura.data.node.Tag;
import cn.ismartv.sakura.ui.fragment.NodeFragment;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by fenghb on 14-7-4.
 */
public class NetWorkUtilities {
    private static final String TAG = "NetWorkUtilities";

    private static final String HOST = "http://192.168.16.103:8099";
    private static final String GET_NODE_URL = "/shipinkefu/getCdninfo";


    public static void getTag() {

        new Thread() {
            @Override
            public void run() {
                Log.d(TAG, "getTag is running......");
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(HOST + GET_NODE_URL + "?actiontype=gettag");
                HttpResponse response = null;
                try {
                    response = client.execute(get);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                    NodeFragment.messageHandler.sendEmptyMessage(NodeFragment.CONNECTION_REFUSED);
                    return;
                }
                if (response.getStatusLine().
                        getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    String result = new String();
                    try {
                        result = EntityUtils.toString(entity);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, result);
                    Tag tag = new Gson().fromJson(result, Tag.class);
                    if (tag.isChanged()) {
                        getNodeList();
                    } else {
                        getNodeList();
                    }

                } else {
                    Log.e(TAG, response.getStatusLine().toString());
                }
            }
        }.start();
    }


    public static void getNodeList() {
        new Thread() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(HOST + GET_NODE_URL + "?actiontype=getcdnlist");
                HttpResponse response = null;
                try {
                    response = client.execute(get);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                    NodeFragment.messageHandler.sendEmptyMessage(NodeFragment.CONNECTION_REFUSED);
                    return;
                }
                if (response.getStatusLine().
                        getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    String result = new String();
                    try {
                        result = EntityUtils.toString(entity);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, result);

                    Nodes nodes = new Gson().fromJson(result, Nodes.class);

                    Message message = NodeFragment.messageHandler.obtainMessage(NodeFragment.GET_NODE_LIST_COMPLETE, nodes);
                    NodeFragment.messageHandler.sendMessage(message);
//                    CacheManager.updateNodeCache();
                    HttpDownload httpDownload = new HttpDownload();
                    httpDownload.download(nodes);
                } else {
                    Log.e(TAG, response.getStatusLine().toString());
                }
            }
        }.start();
    }
}
