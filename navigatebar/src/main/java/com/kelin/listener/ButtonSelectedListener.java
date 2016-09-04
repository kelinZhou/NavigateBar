package com.kelin.listener;

/**
 * @描述 状态栏的回掉监听接口；
 * @创建人 kelin
 * @创建时间 2016/9/3  21:46
 * @包名 com.kelin.listener
 */
public interface ButtonSelectedListener {
    /**
     * 当导航栏按钮被选中后调用此方法；
     * @param position 当前被点击的按钮的索引，这个索引也是调用{@link com.kelin.navigatebar.NavigateBar#addButton(int, int, String, boolean, String)
     * addButton(int normalBitmap, int selectedBitmap, String title, boolean specific, String tag)}
     *                 方法后被添加的顺序,无论你添加的是特殊按钮还是普通按钮；
     * @param isSpecific 这个参数是告诉你当前你选中的按钮是不是一个特殊按钮。<b color="blue">true</b>表示当前你选中的是一个特殊按钮，
     *                   <p> <b color="blue">false</b>表示当前你选中的是常规按钮；
     * @param tag 这个参数是由NavigateBar的创建者在调用{@link com.kelin.navigatebar.NavigateBar#addButton(int, int, String, boolean, String)
     *                 addButton(int normalBitmap, int selectedBitmap, String title, boolean specific, String tag)}
     *                 方法时传入的；
     * @param buttonId 这个参数是当前被点击的按钮的ID，每个按钮都有一个对应的ID。而且不同类型的按钮的ID是有区分的，每个类型
     *                 的ID默认都是从0开始自动增长的，这个增长的顺序与调用调用{@link com.kelin.navigatebar.NavigateBar#addButton(int, int, String, boolean, String)
     *                 addButton(int normalBitmap, int selectedBitmap, String title, boolean specific, String tag)}
     *                 方法的顺序是相同的；
     */
    void onSelected(int position, boolean isSpecific, String tag, int buttonId);
}
