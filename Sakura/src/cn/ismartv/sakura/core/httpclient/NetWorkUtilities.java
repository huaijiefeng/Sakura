package cn.ismartv.sakura.core.httpclient;

import android.util.Log;
import cn.ismartv.sakura.data.HttpData;
import cn.ismartv.sakura.data.Node;
import cn.ismartv.sakura.data.NodeTag;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fenghb on 14-7-4.
 */
public class NetWorkUtilities {
    private static final String TAG = "NetWorkUtilities";
    private static final String HOST = "http://192.168.16.103:8099";
    private static final String GET_NODE_URL = "/shipinkefu/getCdninfo";


    public static boolean nodeIsChanged() {
        Log.d(TAG, "get node tag is running...");
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(HOST + GET_NODE_URL + "?actiontype=gettag");
        HttpResponse response;
        String result = null;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);

                Log.d(TAG, result);
            } else {
                Log.e(TAG, response.getStatusLine().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        NodeTag tag = new Gson().fromJson(result, NodeTag.class);
        Log.d(TAG, "get node tag is end...");
        return tag.isChanged();
    }

    public static ArrayList<Node> getNodeList() {
        Log.d(TAG, "get node list is running...");
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(HOST + GET_NODE_URL + "?actiontype=getcdnlist");
        HttpResponse response;
        String result = null;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                Log.d(TAG, result);
            } else {
                Log.e(TAG, response.getStatusLine().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpData httpData = new Gson().fromJson(result, HttpData.class);
        Log.d(TAG, "get node tag is end...");
        return httpData.getCdn_list();
    }



}
