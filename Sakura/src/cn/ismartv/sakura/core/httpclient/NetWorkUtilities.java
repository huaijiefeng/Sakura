package cn.ismartv.sakura.core.httpclient;

import android.content.Entity;
import android.util.Log;
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

    private static final String HOST = "http://192.168.1.5:8083";
    private static final String GET_NODE_URL = "/GetCdnList?Type1";

    public static void getNodeList() {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(HOST + GET_NODE_URL);
        HttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            String result = new String();
            try {
                result = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG, result);
        } else {
            Log.e(TAG,response.getStatusLine().toString());
        }
    }
}
