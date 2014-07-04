package cn.ismartv.sakura.core.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by fenghb on 14-7-4.
 */
public class NetWorkUtilities {
    private static final String TAG = "NetWorkUtilities";

    private static final String HOST = "http://192.168.1.5:8083";
    private static final String GET_NODE_URL = "/GetCdnList?Type1";

    public static void getNodeList() {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet();


    }


}
