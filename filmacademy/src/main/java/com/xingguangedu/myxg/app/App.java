package com.xingguangedu.myxg.app;

import android.app.Activity;
import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.util.ArrayList;

/**
 *@description:项目的Application
 *@author:袁东华
 *created at 2016/8/22 0022 上午 10:34
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.
    }
    private static ArrayList<Activity> list=new ArrayList<>();
    public static void addActivity(Activity activity){
        list.add(activity);
    }
    public static void exitApp(){
        if (list!=null&&list.size()>0){
            for (int i=0;i<list.size();i++){
                list.get(i).finish();
            }
        }
        System.exit(0);
    }
}
