package starpark.filmacademy.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


import org.xutils.x;

import starpark.filmacademy.R;
import starpark.filmacademy.utils.ManageUserDataUtil;
import starpark.filmacademy.view.dialog.ProgressDialog;

/**
 * @description:基础activity
 * @author:袁东华 created at 2016/8/22 0022 上午 11:36
 */
public class BaseActivity extends AppCompatActivity {
    //Intent传递的字符串参数名
    public final String F = "flag";
    public Activity activity = BaseActivity.this;


    private boolean checkLogin = false;



    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
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


    public boolean checkPermission(final String permissionName, final int request_permission) {
        //检查权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            return true;
        }
        if (checkSelfPermission(permissionName) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(permissionName)) {
            Snackbar.make(null, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{permissionName}, request_permission);
                        }
                    });
        } else {
            requestPermissions(new String[]{permissionName}, request_permission);
        }
        return false;
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
        checkLogin();
    }
}
