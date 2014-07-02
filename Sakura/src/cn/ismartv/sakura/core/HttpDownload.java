package cn.ismartv.sakura.core;

import android.os.Environment;
import android.util.Log;
import cn.ismartv.sakura.utils.DevicesUtilities;
import cn.ismartv.sakura.utils.StringUtilities;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by fenghb on 14-7-2.
 */
public class HttpDownload {
    private static final String TAG = "HttpDownload";

    public native Map<String, String> downloadForSingleThread(Map<String, String> map);

    public native List<Map<String, String>> multiDownloadForSingleThread(List<Map<String, String>> list);


    public void download(List<Map<String, String>> list) {
        Log.d(TAG, "download method is executing......");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<List<Map<String, String>>> future = executorService.submit(new DownloadTask(list));
    }

}
