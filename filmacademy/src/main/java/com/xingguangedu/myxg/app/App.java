package com.xingguangedu.myxg.app;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.ALIAS_TYPE;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.xingguangedu.myxg.utils.ManageUserDataUtil;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.util.ArrayList;


/**
 * @description:项目的Application
 * @author:袁东华 created at 2016/8/22 0022 上午 10:34
 */
public class App extends Application {
    private PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.

        //务必在工程的Application类的 onCreate() 方法中注册推送服务，无论推送是否开启都需要调用此方法：
        mPushAgent = PushAgent.getInstance(this);
        //是否输出日志
        mPushAgent.setDebugMode(true);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {

                //注册成功会返回device token
//                device token是友盟生成的用于标识设备的id，长度为44位，不能定制和修改。同一台设备上不同应用对应的device token不一样。
//                如需手动获取device token，可以调用mPushAgent.getRegistrationId()方法（需在注册成功后调用）。
                if (!TextUtils.isEmpty(deviceToken)) {

                    ManageUserDataUtil.getInstance().setDeviceCode(deviceToken);


                }


            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        //设置场景
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    private static ArrayList<Activity> list = new ArrayList<>();

    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    public static void exitApp() {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).finish();
            }
        }

        System.exit(0);
    }
}
