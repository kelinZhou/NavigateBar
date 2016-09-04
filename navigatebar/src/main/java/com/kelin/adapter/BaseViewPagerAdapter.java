package com.kelin.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @描述 ViewPager适配器的基类。
 * @创建人 kelin
 * @创建时间 2016/9/3  22:21
 * @包名 com.kelin.adapter
 */
public abstract class BaseViewPagerAdapter<T> extends PagerAdapter {
    /**
     * 这个集合是用来存放所有Page页面对象的；
     */
    protected final List<T> mPagers;

    public BaseViewPagerAdapter (List<T> pagers){
        mPagers = pagers;
    }

    @Override
    public int getCount() {
        return mPagers == null ? 0 : mPagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 初始化页面方法；
     * @param container 当前的ViewPager对象；
     * @param position 当前将要加载的页面的索引；
     * @return 需要返回一个View布局，返回的这个布局会被加载到当前ViewPager的当前页；
     */
    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = onCreateView(container, position, mPagers.get(position));
        if (view == null) {
            throw new RuntimeException("onCreateView(ViewGroup container, int position)方法不能返回null");
        }
        container.addView(view);
        return view;
    }

    /**
     * 当一个页面被加载的时候调用此方法，重写此方法返回一个布局后就不用再调用
     * {@link #instantiateItem(ViewGroup, int)}方法了，否则又可能出现无法预知的错误；
     * @param container 当前的ViewPager对象；
     * @param position 当前将要加载的页面的索引；
     * @param t 该参数是一个对象，这个对象是不确定的，是子类在继承{@link BaseViewPagerAdapter}的时候所制定的泛型；
     * @return 需要返回一个View布局，返回的这个布局会被加载到当前ViewPager的当前页；
     */
    protected abstract View onCreateView(ViewGroup container, int position, T t);
}
