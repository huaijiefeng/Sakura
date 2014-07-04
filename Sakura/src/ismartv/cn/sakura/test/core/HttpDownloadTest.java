package ismartv.cn.sakura.test.core;

import android.test.AndroidTestCase;
import android.util.Log;
import cn.ismartv.sakura.core.download.DownloadTask;
import cn.ismartv.sakura.core.download.HttpDownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenghb on 14-7-2.
 */
public class HttpDownloadTest extends AndroidTestCase {
    private static final String TAG = "HttpDownloadTest";

    @Override
    protected void setUp() throws Exception {
        download();
        super.setUp();
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private void download() {
        HttpDownload httpDownload = new HttpDownload();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put(DownloadTask.NODE_IP, "dldir1.qq.com");
        map.put(DownloadTask.NODE_URL, "http://dldir1.qq.com/qqfile/qq/QQ5.5/11433/QQ5.5.exe");
        list.add(map);
    }
}
