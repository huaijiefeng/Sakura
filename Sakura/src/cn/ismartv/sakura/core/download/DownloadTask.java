package cn.ismartv.sakura.core.download;

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
import java.util.ArrayList;
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
    public static final String NODE_SPEED = "speed";
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
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        for (Map<String, String> map : list) {
            String url = map.get(NODE_URL);
            String ip = map.get(NODE_IP);
            if ((!StringUtilities.isEmpty(url)) && DevicesUtilities.isExistSDCard()) {
                HttpGet get = new HttpGet(url);
                Log.d(TAG, "http get url is : " + url);
                try {
                    long startTime = System.currentTimeMillis();
                    HttpResponse response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long size = entity.getContentLength();
                    InputStream inputStream = entity.getContent();
                    //if inputStream is null
                    if (null != inputStream) {
                        String filename = ip + SUFFIX;
                        File file = new File(DevicesUtilities.getAppCacheDirectory(), filename);
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int location;
                        while ((location = inputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, location);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        inputStream.close();
                        long stopTime = System.currentTimeMillis();
                        map.put(NODE_SPEED, getKBperSECOND(size,startTime,stopTime));
                        results.add(map);
                        Log.d(TAG, "\nfile name : " + filename + "\n" +
                                        "file size : " + size + " byte \n" +
                                        "start time : " + startTime + "\n" +
                                        "stop time : " + stopTime + "\n"
                        );
                    } else {
                        Log.e(TAG, "not get inputstream from http entity content!!!");
                    }
                } catch (FileNotFoundException e) {
                    Log.e(TAG, "FileNotFoundException : " + e.getMessage());
                } catch (ClientProtocolException e) {
                    Log.e(TAG, "ClientProtocolException : " + e.getMessage());
                } catch (IOException e) {
                    Log.e(TAG, "IOException : " + e.getMessage());
                }
            } else {
                Log.e(TAG, "url or sdcard is not available!!!");
            }
        }
        Log.d(TAG, "call method is end!!!");
        return results;
    }

    // calculate download speed
    private final String getKBperSECOND(long dataByte, long start, long stop) {
        return String.valueOf(((float) dataByte) / ((float) (stop - start)) * (1024f / 1000f));
    }
}