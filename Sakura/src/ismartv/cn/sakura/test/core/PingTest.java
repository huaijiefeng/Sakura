package ismartv.cn.sakura.test.core;

import android.test.AndroidTestCase;
import android.util.Log;
import cn.ismartv.sakura.core.ping.Ping;

/**
 * Created by fenghb on 14-7-4.
 */
public class PingTest extends AndroidTestCase {
    private static final String TAG = "PingTest";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ping();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void ping() {
        Ping ping = new Ping();
        Log.d(TAG, String.valueOf(ping.ping()));
    }
}
