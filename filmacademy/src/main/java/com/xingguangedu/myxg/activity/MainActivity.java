package com.xingguangedu.myxg.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.adapter.MainPagerAdapter;
import com.xingguangedu.myxg.app.App;
import com.xingguangedu.myxg.view.tab.TabLayout;

/**
 * @description:开票界面
 * @author:袁东华 created at 2016/7/13 0013 下午 1:26
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.container)
    private ViewPager container;
    @ViewInject(R.id.tabs)
    private TabLayout tabs;


    //tab的图标
    private int[] tabImage = new int[]{
            R.drawable.tab_list_selector,
            R.drawable.tab_category_selector,
            R.drawable.tab_download_selector,
            R.drawable.tab_me_selector};

    private MainPagerAdapter mainPagerAdapter;

    @Override
    public void setCheckLogin(boolean checkLogin) {
        super.setCheckLogin(true);
    }

    @Override
    public void initTopView(String title) {
        super.initTopView(title);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setTitle("美育星光");
        }
    }

    @Override
    public void initView() {
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        container.setAdapter(mainPagerAdapter);
        //是否约束TabLayout最低高度
        tabs.restrainHeight(true);
        //设置TabView水平展示内容
        tabs.setOrientation(LinearLayout.HORIZONTAL);
        tabs.setupWithViewPager(container);
        try {
            //给tab设置自定义View
            for (int i = 0; i < tabs.getTabCount(); i++) {
                TabLayout.Tab tab = tabs.getTabAt(i);
                tab.setIcon(tabImage[i]);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {


    }



    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (!progressDialog.isShowing()) {
                if (System.currentTimeMillis() - exitTime > 1000) {
                    exitTime = System.currentTimeMillis();
                    Toast.makeText(activity, "双击返回键退出应用", Toast.LENGTH_SHORT).show();
                } else {
                    App.exitApp();
                }
            } else {
                Toast.makeText(this, R.string.being_processed, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

