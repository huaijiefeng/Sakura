package cn.ismartv.sakura.core;

import android.util.Log;
import cn.ismartv.sakura.utils.DevicesUtilities;
import cn.ismartv.sakura.utils.StringUtilities;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by fenghb on 14-7-2.
 */
public class DownloadTask implements Callable<List<Map<String, String>>> {
    private static final String TAG = "DownloadTask";

    public static final String NODE_IP = "ip";
    public static final String NODE_URL = "url";
    private static final String SUFFIX = ".ismartv";


    private List<Map<String, String>> list;

    public DownloadTask(List<Map<String, String>> list) {
        this.list = list;
        if (list.isEmpty())
            Log.d(TAG, "download list is empty!!!");
    }

    @Override
    public List<Map<String, String>> call() {
        Log.d(TAG, "call method is executing......");
        HttpClient client = new DefaultHttpClient();
        for (Map<String, String> map : list) {
            String url = map.get(NODE_URL);
            String ip = map.get(NODE_IP);
            if ((!StringUtilities.isEmpty(url)) && DevicesUtilities.isExistSDCard()) {
                HttpGet get = new HttpGet(url);
                Log.d(TAG, "http get url is : " + url);
                HttpResponse response = null;
                try {
                    response = client.execute(get);

                    HttpEntity entity = response.getEntity();
                    long size = entity.getContentLength();
                    InputStream inputStream = null;

                    inputStream = entity.getContent();

                    //if inputStream is null
                    if (null != inputStream) {
                        File file = new File(DevicesUtilities.getAppCacheDirectory(), ip + SUFFIX);
                        FileOutputStream fileOutputStream = null;

                        fileOutputStream = new FileOutputStream(file);

                        byte[] buffer = new byte[1024];
                        int location = -1;
                        int count = 0;
                        while ((location = inputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, location);
                            count += location;
                        }

                        fileOutputStream.flush();


                        fileOutputStream.close();


                        inputStream.close();
                    } else {
                        Log.e(TAG, "not get inputstream from http entity content!!!");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e(TAG, "url or sdcard is not available!!!");
            }
        }
        Log.d(TAG, "call method is end!!!");
        return null;
    }
}