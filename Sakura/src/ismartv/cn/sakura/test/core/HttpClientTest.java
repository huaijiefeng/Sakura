package ismartv.cn.sakura.test.core;

import android.test.AndroidTestCase;
import cn.ismartv.sakura.core.httpclient.NetWorkUtilities;

/**
 * Created by fenghb on 14-7-4.
 */
public class HttpClientTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
//        getNodeList();
        getTag();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private void getNodeList() {
        NetWorkUtilities.getNodeList();
    }

    private void getTag() {
        NetWorkUtilities.getTag();
    }
}
