package com.kelin.navigatebar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kelin.adapter.BaseViewPagerAdapter;
import com.kelin.bean.Button;
import com.kelin.listener.ButtonSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 这是一个自定义的导航栏。
 * @创建人 kelin
 * @创建时间 2016/8/26  18:45
 * @包名 com.kelin.navigatebar
 */
public class NavigateBar extends LinearLayout implements ViewPager.OnPageChangeListener {

    /**
     * 按钮被选中或者单击后的回调对象；
     */
    private ButtonSelectedListener mListener;

    /**
     * 按钮未被选中时的字体颜色；
     */
    private int mNormalColor = Color.BLACK;
    /**
     * 按钮被选中时的字体颜色；
     */
    private int mSelectedColor = Color.BLUE;
    /**
     * 用来存放当前导航栏按钮的集合；
     */
    private List<Button> mButtons = new ArrayList<>();
    /**
     * 用来记录当前被选中的页面的索引；
     */
    private int mCurrentPosition;
    /**
     * 上一次被选中的普通按钮；
     */
    private int mNormalButtonPosition;
    /**
     * 用来记录当前的ViewPager对象；
     */
    private ViewPager mViewPager;

    /**
     * 设置默认显示的页面；
     * @param normalIndex 需要一个索引，如果您是使用该控件管理的ViewPager，
     *                    那么这个索引就应该是ViewPager的索引，这表示你希望默认加载ViewPager的第几页。
     *                    如果你使用该控件管理的是Fragment，那么这个索引就应该是你添加Fragment的顺序。
     */
    public void setNormalSelectedIndex(int normalIndex) {
        mNormalSelectedButtonId = normalIndex;
    }

    /**
     * 默认被选中的Button的ID；
     */
    private int mNormalSelectedButtonId;


    public NavigateBar(Context context) {
        this(context, null);
    }

    public NavigateBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigateBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置方向为水平方向。
        setOrientation(HORIZONTAL);
    }

    /**
     * 设置ViewPager,如果您是希望通过本控件的按钮控制ViewPager的页面切换的话，调用该方法；
     * @param viewPager 一个ViewPager对象；
     * @param pagerAdapter 一个{@link BaseViewPagerAdapter}对象，如果您通过调用此方法给状态栏
     *                     设置ViewPager的话，就不用调用
     *                     {@link ViewPager#setAdapter(PagerAdapter)}方法了，因为此方法会帮您
     *                     做了这件事；
     */
    public void setViewPager (ViewPager viewPager, BaseViewPagerAdapter pagerAdapter){
        mViewPager = viewPager;
        if (pagerAdapter != null) {
            mViewPager.setAdapter(pagerAdapter);
        }
        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * 设置ViewPager,如果您是希望通过本控件的按钮控制ViewPager的页面切换的话，调用该方法；
     * @param viewPager 一个ViewPager对象，在调用这个方法之前您必须要给ViewPager设置适配器
     *               ，如果您没有给ViewPager设置适配器的话将会抛出一个{@link RuntimeException}异常。
     *               给ViewPager设置Adapter建议您是{@link BaseViewPagerAdapter}的子类；
     */
    public void setViewPager (ViewPager viewPager){
        setViewPager(viewPager, null);
    }

    /**
     * 添加一个图标没有Selector效果的导航按钮。
     * @param id 默认的按钮的图标ID；
     * @param title 按钮的标题，可以为<b color="blue">null</b>如果为<b color="blue">null</b>表示这个按钮没有标题；
     * @param tag 给这个按钮设置一个标记，这个标记会在回调方法
     * {@link ButtonSelectedListener#onSelected(int, boolean, String, int)
     * onSelected(int position, boolean isSpecific, String tag)} 中回传回去，一般用来帮助使用者区分自己添加的按钮；
     */
    public void addButton(@DrawableRes int id, String title, String tag) {
        addButton(id, id, title, tag);
    }

    /**
     * 添加一个导航按钮。
     * @param normalId 默认的按钮的图标ID；
     * @param selectedId 按压时的按钮的图标ID；
     * @param title 按钮的标题，可以为<b color="blue">null</b>如果为<b color="blue">null</b>表示这个按钮没有标题；
     * @param tag 给这个按钮设置一个标记，这个标记会在回调方法
     * {@link ButtonSelectedListener#onSelected(int, boolean, String, int)
     * onSelected(int position, boolean isSpecific, String tag)} 中回传回去，一般用来帮助使用者区分自己添加的按钮；
     */
    public void addButton(@DrawableRes int normalId, @DrawableRes int selectedId, String title, String tag) {
        addButton(normalId, selectedId, title, false, tag);
    }

    /**
     * 添加一个按钮。
     * @param normalId 默认的按钮的图标ID；
     * @param selectedId 按压时的按钮的图标ID；
     * @param title 按钮的标题，可以为<b color="blue">null</b>如果为<b color="blue">null</b>表示这个按钮没有标题；
     * @param specific 是否为特殊按钮，true表示添加一个特殊按钮，false表示添加一个常规按钮。
     *  <p><font color="blue">特殊按钮：</font></b>这个按钮被点击时不会切换页面，
     *      而是会调用{@link ButtonSelectedListener#onSelected(int, boolean, String, int) onSelected(int position)}方法；
     * @param tag 给这个按钮设置一个标记，这个标记会在回调方法
     * {@link ButtonSelectedListener#onSelected(int, boolean, String, int)
     * onSelected(int position, boolean isSpecific, String tag)} 中回传回去，一般用来帮助使用者区分自己添加的按钮；
     */
    public void addButton(@DrawableRes int normalId, @DrawableRes int selectedId, String title, boolean specific, String tag) {
        addView(getNavigateButton(normalId, selectedId, title, specific, tag));
    }

    /**
     * 添加一个按钮。
     * @param normalId 默认的按钮的图标ID；
     * @param selectedId 按压时的按钮的图标ID；
     * @param specific 是否为特殊按钮，true表示添加一个特殊按钮，false表示添加一个常规按钮。
     *  <p><font color="blue">特殊按钮：</font></b>这个按钮被点击时不会切换页面，
     *      而是会调用{@link ButtonSelectedListener#onSelected(int, boolean, String, int) onSelected(int position)}方法；
     * @param tag 给这个按钮设置一个标记，这个标记会在回调方法
     * {@link ButtonSelectedListener#onSelected(int, boolean, String, int)
     * onSelected(int position, boolean isSpecific, String tag)} 中回传回去，一般用来帮助使用者区分自己添加的按钮；
     */
    public void addButton(@DrawableRes int normalId, @DrawableRes int selectedId, boolean specific, String tag) {
        addView(getNavigateButton(normalId, selectedId, null, specific, tag));
    }

    /**
     * 添加一个图标没有Selector效果的按钮。
     * @param bitmap 默认的按钮的图标ID；
     * @param title 按钮的标题，可以为<b color="blue">null</b>如果为<b color="blue">null</b>表示这个按钮没有标题；
     * @param specific 是否为特殊按钮，true表示添加一个特殊按钮，false表示添加一个常规按钮。
     *  <p><font color="blue">特殊按钮：</font></b>这个按钮被点击时不会切换页面，
     *      而是会调用{@link ButtonSelectedListener#onSelected(int, boolean, String, int) onSelected(int position)}方法；
     * @param tag 给这个按钮设置一个标记，这个标记会在回调方法
     * {@link ButtonSelectedListener#onSelected(int, boolean, String, int)
     * onSelected(int position, boolean isSpecific, String tag)} 中回传回去，一般用来帮助使用者区分自己添加的按钮；
     */
    public void addButton(int bitmap, String title, boolean specific, String tag) {
        addButton(bitmap, bitmap, title, false, tag);
    }

    /**
     * 添加一个没有标题按钮。
     * @param bitmap 默认的按钮的图标ID；
     * @param specific 是否为特殊按钮，true表示添加一个特殊按钮，false表示添加一个常规按钮。
     *  <p><font color="blue">特殊按钮：</font></b>这个按钮被点击时不会切换页面，
     *      而是会调用{@link ButtonSelectedListener#onSelected(int, boolean, String, int) onSelected(int position)}方法；
     * @param tag 给这个按钮设置一个标记，这个标记会在回调方法
     * {@link ButtonSelectedListener#onSelected(int, boolean, String, int)
     * onSelected(int position, boolean isSpecific, String tag)} 中回传回去，一般用来帮助使用者区分自己添加的按钮；
     */
    public void addButton(int bitmap, boolean specific, String tag) {
        addButton(bitmap, bitmap, null, specific, tag);
    }

    /**
     * 获取一个布局，这个布局将被填充到状态栏中做为按钮。
     *
     * @param normalBitmap 默认的按钮的图标；
     * @param selectedBitmap 按下时的按钮的图标，如果您不希望按钮状态改变是按钮的图标也跟着改变的话，可以将该参数设置为-1；
     * @param title 按钮的标题；
     * @param tag 给这个按钮设置一个标记，这个标记会在回调方法
     * {@link ButtonSelectedListener#onSelected(int, boolean, String, int)
     * onSelected(int position, boolean isSpecific, String tag)} 中回传回去，一般用来帮助使用者区分自己添加的按钮；
     * @return 返回一个View;
     */
    private View getNavigateButton(@DrawableRes int normalBitmap, @DrawableRes int selectedBitmap,
                                   String title, final boolean isSpecific, String tag) {
        final LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(VERTICAL);
        LayoutParams params;
        params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setLayoutParams(params);

        //创建ImageView。
        params = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //给ImageView设置图标
        StateListDrawable drawable = new StateListDrawable();
        Drawable normal = getResources().getDrawable(normalBitmap);
        Drawable selected = getResources().getDrawable(selectedBitmap == -1 ? normalBitmap : selectedBitmap);
        if (isSpecific) {
            drawable.addState(new int[]{android.R.attr.state_pressed}, selected);
            drawable.addState(new int[]{-android.R.attr.state_pressed}, normal);
        } else {
            drawable.addState(new int[]{android.R.attr.state_selected}, selected);
            drawable.addState(new int[]{-android.R.attr.state_selected}, normal);
        }
        imageView.setImageDrawable(drawable);
        layout.addView(imageView);

        //设置标题
        TextView textView = null;
        if (!TextUtils.isEmpty(title)) {
            params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            textView = new TextView(getContext());
            textView.setLayoutParams(params);
            textView.setText(title);
            textView.setTextColor(mNormalColor);
            layout.addView(textView);
        }

        final Button button = new Button(mButtons.size(), getButtonId(isSpecific), isSpecific, tag, layout, imageView, textView);
        mButtons.add(button);

        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getIndex() != mNormalButtonPosition || isSpecific) {
                    changePager(button);
                }
            }
        });
        return layout;
    }

    /**
     * 改变当前的页面；
     * @param button 需要一个{@link Button}对象；
     */
    private void changePager(Button button) {
        selectedButton(button);
        //切换ViewPager的页面
        changeViewPage(button);
        //监听回调
        if (mListener != null) {
            mListener.onSelected(button.getIndex(), button.isSpecific(), button.getTag(), button.getId());
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //处理默认加载的页面这个页面是根据用户设置加载的，如果用户没有设置，默认加载第1页。
        Button btn = null;
        for (Button button : mButtons) {
            if (button.getId() == mNormalSelectedButtonId) {
                btn = button;
                break;
            }
        }
        changePager(btn == null ? mButtons.get(0) : btn);
    }

    /**
     * 让一个按钮被选中；
     * @param button 需要一个Button对象。
     */
    private void selectedButton(Button button) {
        if (!button.isSpecific()) {
            //如果当前点击的不是特殊按钮，那么就将上一次点击的按钮恢复到初始化状态；
            Button btn = mButtons.get(mNormalButtonPosition);
            TextView tv1 = btn.getTitle();
            if (tv1 != null) {
                tv1.setTextColor(mNormalColor);
            }
            //如果上一次的按钮不是特殊按钮就将图标设置为未选中状态，否则不需要设置。因为特殊按钮的图片改变是根据
            // pressed状态判断的;
            if (!btn.isSpecific()) {
                btn.getIcon().setSelected(false);
            }
            mNormalButtonPosition = button.getIndex();
        }
        mCurrentPosition = button.getIndex();

        TextView tv2 = button.getTitle();
        if (tv2 != null) {
            tv2.setTextColor(mSelectedColor);
        }

        if (!button.isSpecific()) {
            button.getIcon().setSelected(true);
        }
    }

    /**
     * 改变当前ViewPager的CurrentItem;
     * @param button 需要一个{@link Button}对象；
     */
    private void changeViewPage(Button button) {
        if (mViewPager != null && !button.isSpecific()) {
            PagerAdapter adapter = mViewPager.getAdapter();
            if (adapter != null) {
                int id = button.getId();
                if (id < adapter.getCount()) {
                    mViewPager.setCurrentItem(id);
                }
            } else {
                throw new RuntimeException("Must be set to Adapter ViewPager.");
            }
        }
    }

    /**
     * 给Button创建一个ID，这个ID是根据不同的按钮类型分配的；
     * @param isSpecific 是否为获取特殊按钮ID，<b color="blue">true</b>为特殊按钮，<b color="blue">false</b>为默认按钮；
     * @return 返回当前按钮类型应该分配的ID；
     */
    private int getButtonId(boolean isSpecific) {
        int id = 0;
        for (int i = 0; i < mButtons.size(); i++) {
            if (mButtons.get(i).isSpecific() == isSpecific) {
                id = i + 1;
            }
        }
        return id;
    }

    /**
     * 设置导航栏按钮被选中的监听；
     * @param listener {@link ButtonSelectedListener 的实现类对象}
     */
    public void setOnButtonSelectedListener(ButtonSelectedListener listener) {
        mListener = listener;
    }

    /**
     * 设置标题的字体颜色；
     * @param normalColor 需要一个int型的Color值；默认为{@link Color#BLACK} 黑色；
     * @param selectedColor 需要一个int型的Color值；默认为{@link Color#BLUE} 蓝色；
     */
    public void setTextColors(int normalColor, int selectedColor) {
        mNormalColor = normalColor;
        mSelectedColor = selectedColor;
    }

    /**
     * 设置默认的字体颜色；
     * @param normalColor 需要一个int型的Color值；默认为{@link Color#BLACK} 黑色；
     */
    public void setNormalTextColor(int normalColor) {
        mNormalColor = normalColor;
    }

    /**
     * 设置选中时字体颜色；
     * @param selectedColor 需要一个int型的Color值；默认为{@link Color#BLUE} 蓝色；
     */
    public void setSelectedTextColor(int selectedColor) {
        mSelectedColor = selectedColor;
    }

    /**
     * 当ViewPager被滑动的时候的回调方法；
     * @param position 当前页面，及你点击滑动的页面；
     * @param positionOffset 当前页面偏移的百分比;
     * @param positionOffsetPixels 当前页面偏移的像素位置;
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    /**
     * 页面跳转完后的回调方法；
     *
     * @param position 当前被选中的页面的索引；
     */
    @Override
    public void onPageSelected(int position) {
        for (Button button : mButtons) {
            if (button.getId() == position) {
                selectedButton(button);
            }
        }
    }

    /**
     * 状态改变的时候的回调方法；
     * @param state 当前的状态，有<b color="blue">0，1，2</b>三种状态。
     *              <p color="blue">parg0 ==1</p> 表示正在滑动。
     *              <p color="blue">arg0==2</p> 表示滑动完毕了。
     *              <p color="blue">arg0==0</p> 表示什么都没做。
     */
    @Override
    public void onPageScrollStateChanged(int state) {}
}
