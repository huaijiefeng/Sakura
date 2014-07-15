package cn.ismartv.sakura.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.ismartv.sakura.R;

/**
 * Created by fenghb on 14-7-15.
 */
public class HelpDetailActivity extends Activity {
    private static final int TOP_LEFT = 0;
    private static final int TOP_RIGHT = 1;
    private static final int BOTTOM_LEFT = 2;
    private static final int BOTTOM_RIGHT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        Intent intent = getIntent();
        switch (intent.getIntExtra("position", -1)) {
            case TOP_LEFT:
                break;
            case TOP_RIGHT:
                break;
            case BOTTOM_LEFT:
                break;
            case BOTTOM_RIGHT:
                break;
            default:
                break;
        }
    }
}
