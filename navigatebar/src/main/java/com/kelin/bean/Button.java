package com.kelin.bean;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @描述 按钮对象类，用来记住按钮的一些属性；
 * @创建人 kelin
 * @创建时间 2016/8/28  18:44
 * @包名 com.kelin.bean
 */
public class Button {
    /**
     * 按钮的索引，这个索引是被add到LinerLayout中的顺序，但与ID不同，不同类型的按钮是没有区分的；
     */
    private int index;
    /**
     * 按钮的ID，这个ID是被Add到LinerLayout中的顺序，但是不同类型的按钮有不同的ID；
     */
    private int id;
    /**
     * 是否是特殊按钮
     */
    private boolean isSpecific;
    /**
     * 一个由创建者自定义的标签，用来区分某一类型或某一个按钮；
     */
    private String tag;
    /**
     * 一个按钮的跟布局；
     */
    private View button;
    /**
     * 一个按钮的图标；
     */
    private ImageView icon;
    /**
     * 一个按钮的标题；
     */
    private TextView title;

    public Button() {

    }

    public Button(int index,int id, boolean isSpecific, String tag, View button, ImageView icon, TextView title) {
        this.index = index;
        this.id = id;
        this.isSpecific = isSpecific;
        this.tag = tag;
        this.button = button;
        this.icon = icon;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSpecific() {
        return isSpecific;
    }

    public void setSpecific(boolean specific) {
        isSpecific = specific;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public View getButton() {
        return button;
    }

    public void setButton(View button) {
        this.button = button;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
