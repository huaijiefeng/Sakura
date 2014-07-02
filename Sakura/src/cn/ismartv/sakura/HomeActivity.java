package cn.ismartv.sakura;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import cn.ismartv.sakura.core.DownloadTask;
import cn.ismartv.sakura.core.HttpDownload;
import cn.ismartv.sakura.ui.fragment.FeedbackFragment;
import cn.ismartv.sakura.ui.fragment.NodeFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends FragmentActivity {
    private static final String[] CONTENT = new String[]{"Node", "Feedback"};
    private static final Fragment[] FRAGMENTS = {new NodeFragment(), new FeedbackFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        download();


        FragmentPagerAdapter adapter = new HomeAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    class HomeAdapter extends FragmentPagerAdapter {
        public HomeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FRAGMENTS[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
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
