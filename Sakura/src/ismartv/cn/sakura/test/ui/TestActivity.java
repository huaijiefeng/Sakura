package ismartv.cn.sakura.test.ui;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.ui.widget.progressbar.SmoothProgressBarUtils;

/**
 * Created by fenghb on 14-7-24.
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        int[] colors = {Color.RED, Color.GREEN};
        float width = 11.0f;
        imageView.setImageDrawable(SmoothProgressBarUtils.generateDrawableWithColors(colors, width));


    }
}
