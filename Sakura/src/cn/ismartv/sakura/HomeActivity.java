package cn.ismartv.sakura;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import cn.ismartv.sakura.ui.fragment.FeedbackFragment;
import cn.ismartv.sakura.ui.fragment.HelpFragment;
import cn.ismartv.sakura.ui.fragment.NodeFragment;
import com.viewpagerindicator.TabPageIndicator;


public class HomeActivity extends FragmentActivity {
    private static final String FILE_URL = "http://210.14.137.56/cdn/speedtest.ts";

    private static final String TAG = "HomeActivity";
    private static final String[] CONTENT = new String[]{"Node", "Feedback", "Help"};
    private static final Fragment[] FRAGMENTS = {new NodeFragment(), new FeedbackFragment(), new HelpFragment()};


    private FragmentPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        adapter = new HomeAdapter(getSupportFragmentManager());

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
}
