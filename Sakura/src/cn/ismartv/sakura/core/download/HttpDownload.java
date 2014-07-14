package cn.ismartv.sakura.core.download;

import android.util.Log;
import cn.ismartv.sakura.data.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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


    public void download(List<Node> nodes) {
        Log.d(TAG, "download method is executing......");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ArrayList<Node> arrayList = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.getCdnID().equals("6"))
                continue;
            Future<Node> future = executor.submit(new DownloadTask(node, 100, 100));
            try {
                arrayList.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        for (Node kk : arrayList) {
            Log.d(TAG, "name : " + kk.getName());
            Log.d(TAG, "speed : " + kk.getSpeed());
        }
    }
}
