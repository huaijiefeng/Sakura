package cn.ismartv.sakura.core.httpclient;

import android.os.Message;
import android.util.Log;
import cn.ismartv.sakura.data.Nodes;
import cn.ismartv.sakura.ui.fragment.NodeFragment;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by fenghb on 14-7-4.
 */
public class NetWorkUtilities {
    private static final String TAG = "NetWorkUtilities";

    private static final String HOST = "http://192.168.16.103:8099";
    private static final String GET_NODE_URL = "/shipinkefu/getCdninfo";

    public static void getNodeList() {
        new Thread() {
            @Override
            public void run() {

                HttpClient client = new DefaultHttpClient();

                HttpGet get = new HttpGet(HOST + GET_NODE_URL);
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
                } else {
                    Log.e(TAG, response.getStatusLine().toString());
                }
            }
        }.start();
    }
}
