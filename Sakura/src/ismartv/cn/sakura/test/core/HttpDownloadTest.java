package ismartv.cn.sakura.test.core;

import android.test.AndroidTestCase;
import cn.ismartv.sakura.core.DownloadTask;
import cn.ismartv.sakura.core.HttpDownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenghb on 14-7-2.
 */
public class HttpDownloadTest extends AndroidTestCase {

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
        map.put(DownloadTask.NODE_IP, "img.ivsky.com");
        map.put(DownloadTask.NODE_URL, "http://img.ivsky.com/img/tupian/pre/201406/05/chayu-001.jpg");
        list.add(map);
        httpDownload.download(list);
    }
}
