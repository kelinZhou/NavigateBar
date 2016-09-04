package com.kelin.navigatebardemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelin.adapter.BaseViewPagerAdapter;
import com.kelin.listener.ButtonSelectedListener;
import com.kelin.navigatebar.NavigateBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ButtonSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigateBar nb_Bar = (NavigateBar) findViewById(R.id.nb_navigateBar);
        ViewPager vp_Pager = (ViewPager) findViewById(R.id.vp_pager);

        final ArrayList<String> strings = new ArrayList<>();
        strings.add("我是第一页");
        strings.add("我是第二页");

        if (vp_Pager != null) {
            vp_Pager.setAdapter(new BaseViewPagerAdapter<String>(strings) {
                @Override
                protected View onCreateView(ViewGroup container, int position, String s) {
                    TextView view = new TextView(getApplicationContext());
                    view.setText(s);
                    view.setTextColor(Color.BLACK);
                    return view;
                }
            });
        }
        if (nb_Bar != null) {
            nb_Bar.setTextColors(Color.BLUE, Color.RED);
            nb_Bar.addButton(R.mipmap.actionbar_unsend_icon, R.mipmap.biz_chat_comment_send, "发送", "发送");
            nb_Bar.addButton(R.mipmap.actionbar_unsend_icon, R.mipmap.biz_chat_comment_send, true, "取消");
            nb_Bar.addButton(R.mipmap.actionbar_unsend_icon, R.mipmap.biz_chat_comment_send, "中心", "中心");
            nb_Bar.setOnButtonSelectedListener(this);
            nb_Bar.setViewPager(vp_Pager);
        }
    }

    @Override
    public void onSelected(int position, boolean isSpecific, String tag, int buttonId) {
        if (BuildConfig.DEBUG) Log.d("MainActivity", "tag:" + tag + " | isSpecific:" + isSpecific + " | position:" + position + " | buttonId:" + buttonId);
    }
}
