package cn.ismartv.sakura.core.download;

import android.util.Log;
import cn.ismartv.sakura.data.Node;
import cn.ismartv.sakura.utils.DevicesUtilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

/**
 * Created by fenghb on 14-7-2.
 */
public class DownloadTask implements Callable<Node> {
    private static final String TAG = "DownloadTask";
    private static final String SUFFIX = ".ismartv";

    private static final int TIME_OVER = 4;

    private Node node;

    private long timer;

    public DownloadTask(Node node, long maxTime, long maxSpeed) {
        this.node = node;
    }

    @Override
    public Node call() {
        Log.d(TAG, "call is running......");
        new Timer().start();
        int bytesum = 0;
        int byteread;

        File fileName;
        URL url;
        try {
            long startTime = System.currentTimeMillis();
            url = new URL(node.getTestFile());
            Log.d(TAG, "url is : " + url);
            fileName = new File(DevicesUtilities.getAppCacheDirectory(), url.getHost() + SUFFIX);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            while ((byteread = inStream.read(buffer)) != -1 && timer < TIME_OVER) {
                bytesum += byteread;
                Log.d(TAG, getSize(bytesum) + " time : " + timer);
                fs.write(buffer, 0, byteread);
            }
            long stopTime = System.currentTimeMillis();
            String speed = getKBperSECOND(bytesum, startTime, stopTime);
            Log.d(TAG, "download size is : " + getSize(bytesum) + " speed is : " + speed);
            node.setSpeed(speed);
            fs.flush();
            fs.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            return node;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    class Timer extends Thread {
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