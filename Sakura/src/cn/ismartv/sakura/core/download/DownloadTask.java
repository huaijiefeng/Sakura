package cn.ismartv.sakura.core.download;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import cn.ismartv.sakura.core.cache.CacheManager;
import cn.ismartv.sakura.provider.NodeCache;
import cn.ismartv.sakura.utils.DevicesUtilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenghb on 14-7-2.
 */
public class DownloadTask extends Thread {
    private static final String TAG = "DownloadTask";
    private static final String SUFFIX = ".ismartv";

    private static final int TIME_OVER = 4;

    private Context context;
    private Cursor cursor;

    private List<Map<String, String>> nodes;



    public DownloadTask(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        nodes = new ArrayList<Map<String, String>>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("cdn_id", cursor.getString(cursor.getColumnIndex(NodeCache.CDN_ID)));
            map.put("url", cursor.getString(cursor.getColumnIndex(NodeCache.URL)));
            nodes.add(map);
        }
    }



    @Override
    public void run() {
        for (Map<String, String> map : nodes) {
            Log.d(TAG, "call is running......");
            Timer timer = new Timer();
            timer.start();
            int bytesum = 0;
            int byteread;

            File fileName;
            URL url;
            try {
                long startTime = System.currentTimeMillis();
                url = new URL(map.get("url"));
                String cndId = map.get("cdn_id");
                Log.d(TAG, "url is : " + url + "cdn id is : " + cndId);
                fileName = new File(DevicesUtilities.getAppCacheDirectory(), url.getHost() + SUFFIX);
                URLConnection conn = url.openConnection();
                InputStream inStream = conn.getInputStream();
                FileOutputStream fs = new FileOutputStream(fileName);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1 && timer.timer < TIME_OVER) {
                    bytesum += byteread;
//                Log.d(TAG, getSize(bytesum) + " time : " + timer);
                    fs.write(buffer, 0, byteread);
                }
                long stopTime = System.currentTimeMillis();
                String speed = getKBperSECOND(bytesum, startTime, stopTime);
                Log.d(TAG, "download size is : " + getSize(bytesum) + " speed is : " + speed);
                //update node cache
                CacheManager.updateNodeCache(context, cndId, speed);
                fs.flush();
                fs.close();
                inStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    class Timer extends Thread {
        private long timer;

        @Override
        public void run() {
            while (timer <= TIME_OVER) {
                try {
                    this.sleep(1000);
                    timer = timer + 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // calculate download speed
    private final String getKBperSECOND(long dataByte, long start, long stop) {
        return String.valueOf(((float) dataByte) / ((float) (stop - start)) * (1024f / 1000f));
    }

    private final String getSize(long data) {
        if (data < 1024)
            return data + " byte";
        else if (1024 <= data && data < 1024 * 1024)
            return ((float) data / 1024) + " kb";
        else
            return ((float) data / 1024 / 1024) + " mb";
    }
}