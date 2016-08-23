package starpark.filmacademy.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import org.xutils.x;

import starpark.filmacademy.R;
import starpark.filmacademy.view.dialog.ProgressDialog;

/**
 * @description:基础activity
 * @author:袁东华 created at 2016/8/22 0022 上午 11:36
 */
public class BaseActivity extends AppCompatActivity {
    //Intent传递的字符串参数名
    public final String F = "flag";
    public Activity activity = BaseActivity.this;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
        progressDialog = new ProgressDialog(this);
        hideBar(true);
        x.view().inject(this);
        initTopView();
        initView();
        receiveIntentData();
        initData();
        initListener();

    }


    public void hideBar(boolean b) {
        if (b) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }else{
        }
    }

    public void receiveIntentData() {
    }


    public void initTopView() {
    }

    ;

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
                Toast.makeText(this,R.string.being_processed, Toast.LENGTH_SHORT).show();
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
}