package cn.ismartv.sakura;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import cn.ismartv.sakura.core.download.DownloadTask;
import cn.ismartv.sakura.core.download.HttpDownload;
import cn.ismartv.sakura.core.httpclient.NetWorkUtilities;
import cn.ismartv.sakura.data.Nodes;
import cn.ismartv.sakura.provider.node.Node;
import cn.ismartv.sakura.ui.fragment.FeedbackFragment;
import cn.ismartv.sakura.ui.fragment.NodeFragment;
import com.google.gson.Gson;
import com.viewpagerindicator.TabPageIndicator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class HomeActivity extends FragmentActivity {
    private static final String FILE_URL = "http://210.14.137.56/cdn/speedtest.ts";

    private static final String TAG = "HomeActivity";
    private static final String[] CONTENT = new String[]{"Node", "Feedback"};
    private static final Fragment[] FRAGMENTS = {new NodeFragment(), new FeedbackFragment()};


    private FragmentPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Node.Cache.CDN_ID, "111");
//        this.getContentResolver().insert(Node.Cache.CONTENT_URI, contentValues);

//        NetWorkUtilities.getTag();


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
