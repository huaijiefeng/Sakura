package cn.ismartv.sakura.ui.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import cn.ismartv.sakura.R;
import cn.ismartv.sakura.ui.activity.HelpDetailActivity;

/**
 * Created by fenghb on 14-7-15.
 */
public class HelpFragment extends Fragment implements View.OnClickListener {
    public static final int[] POSITION = {0, 1, 2, 3};
    private Button topLeft;
    private Button topRight;
    private Button bottomLeft;
    private Button bottomRight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_help, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topLeft = (Button) view.findViewById(R.id.top_left);
        topRight = (Button) view.findViewById(R.id.top_right);
        bottomLeft = (Button) view.findViewById(R.id.bottom_left);
        bottomRight = (Button) view.findViewById(R.id.bottom_right);
        topLeft.setOnClickListener(this);
        topRight.setOnClickListener(this);
        bottomLeft.setOnClickListener(this);
        bottomRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), HelpDetailActivity.class);
        switch (view.getId()) {
            case R.id.top_left:
                intent.putExtra("position", POSITION[0]);
                break;
            case R.id.top_right:
                intent.putExtra("position", POSITION[1]);
                break;
            case R.id.bottom_left:
                intent.putExtra("position", POSITION[2]);
                break;
            case R.id.bottom_right:
                intent.putExtra("position", POSITION[3]);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
