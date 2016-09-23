package com.xingguangedu.myxg.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


import org.xutils.common.util.LogUtil;
import org.xutils.x;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.app.App;
import com.xingguangedu.myxg.utils.AliasTypeUtils;
import com.xingguangedu.myxg.utils.ManageUserDataUtil;
import com.xingguangedu.myxg.view.dialog.ProgressDialog;

/**
 * @description:基础activity
 * @author:袁东华 created at 2016/8/22 0022 上午 11:36
 */
public class BaseActivity extends AppCompatActivity {
    //Intent传递的字符串参数名
    public final String F = "flag";
    public Activity activity = BaseActivity.this;
    public static int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;

    private boolean checkLogin = false;


    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //此方法与统计分析sdk中统计日活的方法无关！请务必调用此方法！
//        如果不调用此方法，不仅会导致按照"几天不活跃"条件来推送失效，
// 还将导致广播发送不成功以及设备描述红色等问题发生。
// 可以只在应用的主Activity中调用此方法，但是由于SDK的日志发送策略，
// 有可能由于主activity的日志没有发送成功，而导致未统计到日活数据。
        PushAgent.getInstance(this).onAppStart();


        App.addActivity(activity);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音

        setAlias();
        checkPermission();
        progressDialog = new ProgressDialog(this);
        hideBar(true);
        x.view().inject(this);
        setCheckLogin(checkLogin);
        receiveIntentData();
        initTopView("");
        initView();
        initData();
        initListener();


    }

    /**
     * @description:设置别名
     * @author:袁东华 created at 2016/9/23 16:32
     */
    private void setAlias() {
        //必须保证设备号获取到了
        if (!TextUtils.isEmpty(ManageUserDataUtil.getInstance().getDeviceCode())
                && !ManageUserDataUtil.getInstance().getHasAlias()
                && !TextUtils.isEmpty(ManageUserDataUtil.getInstance().getUserId(activity))) {
            //设置别名,用于推送
            PushAgent.getInstance(activity).addExclusiveAlias(
                    ManageUserDataUtil.getInstance().getUserId(activity),
                    AliasTypeUtils.MYXG, new UTrack.ICallBack() {
                        @Override
                        public void onMessage(boolean isSuccess, String message) {
                            LogUtil.e("isSuccess:" + isSuccess);
                            LogUtil.e("message:" + message);
                        }
                    });
        }
    }


    public void hideBar(boolean b) {
        if (b) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        } else {
        }
    }

    public void receiveIntentData() {
    }


    public void initTopView(String title) {
        try {


            TextView title_tv = (TextView) findViewById(R.id.title_tv);
            if (title_tv != null) {

                title_tv.setText(title);
            }
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {

                setSupportActionBar(toolbar);
            }
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setDisplayShowTitleEnabled(false);
            }

        } catch (Exception e) {

        }
    }


    public void initView() {
    }


    public void initData() {
    }


    public void initListener() {
    }


    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            LogUtil.e("没有WRITE_EXTERNAL_STORAGE权限");
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            LogUtil.e("有WRITE_EXTERNAL_STORAGE权限");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (!progressDialog.isShowing()) {
                finish();
            } else {
                Toast.makeText(this, R.string.being_processed, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击返回按钮
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @description:设置是否检查登陆
     * @author:袁东华 created at 2016/8/29 0029 上午 11:45
     */
    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;

    }

    public void checkLogin() {
        if (!ManageUserDataUtil.getInstance().isLogin(activity) && checkLogin) {
            startActivity(new Intent(activity, LoginActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(activity);
        checkLogin();


    }

    @Override
    protected void onPause() {
        super.onPause();
//        当应用在后台运行超过30秒（默认）再回到前端，将被认为是两个独立的session(启动)，
// 例如用户回到home，或进入其他程序，经过一段时间后再返回之前的应用。
// 可通过接口：MobclickAgent.setSessionContinueMillis(long interval) 来自定义这个间隔（参数单位为毫秒）。
//        如果开发者调用Process.kill或者System.exit之类的方法杀死进程，
// 请务必在此之前调用MobclickAgent.onKillProcess(Context context)方法，用来保存统计数据。
        MobclickAgent.onPause(this);

    }

    //用户选择允许或需要后，会回调onRequestPermissionsResult方法, 该方法类似于onActivityResult
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    /**
     * @description: 处理权限获取结果
     * @author:袁东华 created at 2016/9/23 17:25
     */
    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults != null && grantResults.length > 0) {


                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 允许权限
                    LogUtil.e("允许权限");


                } else {
                    // 拒绝权限
                    LogUtil.e("拒绝权限");
                }
            }
        }
    }
}
