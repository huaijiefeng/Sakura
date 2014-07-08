package ismartv.cn.sakura.test.core;

import android.os.Message;
import android.test.AndroidTestCase;
import android.util.Log;
import cn.ismartv.sakura.core.ping.Ping;
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
public class PingTest extends AndroidTestCase {
    private static final String TAG = "PingTest";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        filter();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void ping() {
        Ping ping = new Ping();
//        Log.d(TAG, String.valueOf(ping.ping()));
    }


    public void filter() {

        String HOST = "http://192.168.16.103:8099";
        String GET_NODE_URL = "/shipinkefu/getCdninfo";

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
            Ping ping = new Ping();
            for (Nodes.Node node : nodes.getCdn_list()) {
                String ip = node.getUrl().replace("|", "-").split("-")[0];


                Log.d(TAG, node.getName() + " : " + String.valueOf(ping.ping(ip)) + " ms" + " : " + ip);
            }

        } else {
            Log.e(TAG, response.getStatusLine().toString());
        }

    }


}
