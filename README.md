# NavigateBar
底部状态条自定义控件。您可以使用这个控件来管理ViewPager的页面切换或者Fragment的切换。
## 基本使用
###第一步：
在布局中添加控件

    <com.kelin.navigatebar.NavigateBar
        android:id="@+id/nb_navigateBar"
        android:layout_width="match_parent"
        android:layout_height="60dp"/>

###第二步：
找到控件

    NavigateBar nb_Bar = (NavigateBar) findViewById(R.id.nb_navigateBar);

###第三步：
添加按钮

    //添加一个默认按钮
    nb_Bar.addButton(R.mipmap.bar_search_normal, R.mipmap.bar_search_selected, "搜索", "搜索");
    //添加一个特殊按钮
    nb_Bar.addButton(R.mipmap.bar_home_normal, R.mipmap.bar_home_selected, true, "Home");
    //添加一个默认按钮
    nb_Bar.addButton(R.mipmap.bar_shopping_normal, R.mipmap.bar_shopping_selected, "购物车", "购物车");
###第四步(设置ViewPager)：
####1.给ViewPager设置适配器

	vp_Pager.setAdapter(new BaseViewPagerAdapter<String>(strings) {
                @Override
                protected View onCreateView(ViewGroup container, int position, String s) {
                    TextView view = new TextView(getApplicationContext());
                    view.setText(s);
                    view.setTextColor(Color.BLACK);
                    return view;
                }
            });

####2.设置ViewPager

	nb_Bar.setViewPager(vp_Pager);



## 其他方法
###设置字体颜色

    nb_Bar.setTextColors(Color.BLUE, Color.RED);

###设置默认字体颜色

    nb_Bar.setNormalTextColor(Color.parseColor("#000"));
###设置选中时的字体颜色

    nb_Bar.setSelectedTextColor(Color.parseColor("#F00"));
###设置按钮选中监听，当滑动ViewPager时该监听也会被回调。

    nb_Bar.setOnButtonSelectedListener(new ButtonSelectedListener() {
        @Override
        public void onSelected(int position, boolean isSpecific, String tag, int buttonId) {
            if (BuildConfig.DEBUG) Log.d("MainActivity", "tag:" + tag + " | isSpecific:" + isSpecific + " | position:" + position + " | buttonId:" + buttonId);
            if (isSpecific) {
                Toast.makeText(getApplicationContext(), "这是一个特殊按钮", Toast.LENGTH_SHORT).show();
            }
        }
    });

###设置默认加载页面

	//默认加载第二页，参数中的1表示的是ViewPager的索引；
    nb_Bar.setNormalSelectedIndex(1);
            

    