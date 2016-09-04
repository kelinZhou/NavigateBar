package com.kelin.navigatebardemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
            nb_Bar.addButton(R.mipmap.bar_search_normal, R.mipmap.bar_search_selected, "搜索", "搜索");
            nb_Bar.addButton(R.mipmap.bar_home_normal, R.mipmap.bar_home_selected, true, "Home");
            nb_Bar.addButton(R.mipmap.bar_shopping_normal, R.mipmap.bar_shopping_selected, "购物车", "购物车");
            nb_Bar.setOnButtonSelectedListener(this);
            nb_Bar.setViewPager(vp_Pager);
            nb_Bar.setNormalSelectedIndex(1);
        }
    }

    @Override
    public void onSelected(int position, boolean isSpecific, String tag, int buttonId) {
        if (BuildConfig.DEBUG) Log.d("MainActivity", "tag:" + tag + " | isSpecific:" + isSpecific + " | position:" + position + " | buttonId:" + buttonId);
        if (isSpecific) {
            Toast.makeText(this, "这是一个特殊按钮", Toast.LENGTH_SHORT).show();
        }
    }
}
