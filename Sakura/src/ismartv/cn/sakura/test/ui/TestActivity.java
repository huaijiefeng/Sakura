package ismartv.cn.sakura.test.ui;

import android.app.Activity;
import android.os.Bundle;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.ui.widget.progressbar.SmoothProgressBar;

/**
 * Created by fenghb on 14-7-24.
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        SmoothProgressBar smoothProgressBar = (SmoothProgressBar) findViewById(R.id.node_speed);
        smoothProgressBar.stopProgress();
    }
}
