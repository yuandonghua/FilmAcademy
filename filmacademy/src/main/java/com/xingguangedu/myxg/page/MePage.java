package com.xingguangedu.myxg.page;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.activity.CollectionActivity;
import com.xingguangedu.myxg.activity.FeedbackActivity;
import com.xingguangedu.myxg.activity.HistoryActivity;
import com.xingguangedu.myxg.activity.SettingActivity;

/**
 * @description:个人中心界面
 * @author:袁东华 created at 2016/8/24 0024 下午 3:54
 */
public class MePage {
    private static MePage instance;
    private View view = null;
    private Activity activity = null;


    private MePage() {


    }


    public void start(Activity activity, View view) {
        if (view != null && activity != null && (this.view == null && this.activity == null)) {
            this.activity = activity;
            this.view = view;
            initView();
            initData();
        }
    }

    public View getView() {
        return view;
    }

    private void initData() {

    }

    private void initView() {
        TextView collection_tv = (TextView) view.findViewById(R.id.collection_tv);
        //点击收藏夹
        collection_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, CollectionActivity.class));
            }
        });
        TextView history_tv = (TextView) view.findViewById(R.id.history_tv);
        //点击历史记录
        history_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, HistoryActivity.class));
            }
        });
        TextView setting_tv = (TextView) view.findViewById(R.id.setting_tv);
        //点击设置
        setting_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, SettingActivity.class));
            }
        });
        TextView feedback_tv = (TextView) view.findViewById(R.id.feedback_tv);
        //点击反馈
        feedback_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, FeedbackActivity.class));
            }
        });
    }

    public static MePage getInstance() {
        if (instance == null) {
            synchronized (MePage.class) {
                if (instance == null) {
                    instance = new MePage();
                }
            }
        }

        return instance;
    }
}
