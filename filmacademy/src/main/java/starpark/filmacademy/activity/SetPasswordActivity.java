package starpark.filmacademy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import starpark.filmacademy.R;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.http.UserDataHttp;
import starpark.filmacademy.utils.MD5Util;
import starpark.filmacademy.utils.ManageUserDataUtil;
import starpark.filmacademy.utils.SharedPreferencesUtil;

/**
 * @description:修改密码界面
 * @author:袁东华 created at 2016/8/27 0027 上午 10:57
 */
@ContentView(R.layout.activity_set_password)
public class SetPasswordActivity extends BaseActivity {

    @ViewInject(R.id.password_et)
    private EditText password_et;
    @ViewInject(R.id.confirmPassword_et)
    private EditText confirmPassword_et;
    private String password;
    private String flag = "",phone="";

    @Override
    public void receiveIntentData() {
        flag = getIntent().getStringExtra(F);
        phone= getIntent().getStringExtra("phone");
    }

    @Override
    public void initTopView(String title) {
        super.initTopView("设置密码");
    }


    @Override
    public void initListener() {
        Button save_btn = (Button) findViewById(R.id.save_btn);
        //点击保存
        save_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSave();
            }
        });
    }

    @Override
    public void initData() {
    }

    private void attemptSave() {
        progressDialog.show();
        password_et.setError(null);
        confirmPassword_et.setError(null);

        //获取用户名,密码,确认密码
        password = password_et.getText().toString();
        String confirmPassword = confirmPassword_et.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //检验密码是否为空及是否有效
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            password_et.setError(getString(R.string.error_invalid_password));
            focusView = password_et;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            // 检查密码是否为空
            password_et.setError(getString(R.string.error_no_null_required));
            focusView = password_et;
            cancel = true;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            // 检查确认密码是否为空
            confirmPassword_et.setError(getString(R.string.error_no_null_required));
            focusView = confirmPassword_et;
            cancel = true;
        } else if (!password.equals(confirmPassword)) {
            confirmPassword_et.setError(getString(R.string.error_different_required));
            focusView = confirmPassword_et;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            progressDialog.dismiss();
        } else {
            if ("忘记密码".equals(flag)) {
                String newPwd = MD5Util.getInstance().getMD5String(password);
                UserDataHttp.getInstance().forgetPassword(phone, newPwd, handler,
                        HttpIdentifyingCodeUtil.MODIFY_PASSWORD_S, HttpIdentifyingCodeUtil.MODIFY_PASSWORD_F);
            } else {
                String oldPwd = MD5Util.getInstance().getMD5String(ManageUserDataUtil.getInstance().getUserPassword(activity));
                String newPwd = MD5Util.getInstance().getMD5String(password);
                UserDataHttp.getInstance().modifyPassword(ManageUserDataUtil.getInstance().getUserId(activity),
                        oldPwd, newPwd, handler,
                        HttpIdentifyingCodeUtil.MODIFY_PASSWORD_S, HttpIdentifyingCodeUtil.MODIFY_PASSWORD_F);
            }
        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 4;
    }

    private Handler handler = new Handler() {
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //密码修改成功
                case HttpIdentifyingCodeUtil.MODIFY_PASSWORD_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        String mobile = data.getString("mobile");
                        String id = data.getString("id");
                        String logname = data.getString("logname");
                        String role = data.getString("role");
                        SharedPreferencesUtil.getInstance().setName(activity, logname);
                        SharedPreferencesUtil.getInstance().setId(activity, id);
                        //保存用户名和密码
                        SharedPreferencesUtil.getInstance().setPhone(activity, phone);
                        SharedPreferencesUtil.getInstance().setPassword(activity, password);
                        Toast.makeText(activity, R.string.login_success, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        finish();
                    }

                    break;
                //登陆失败
                case HttpIdentifyingCodeUtil.MODIFY_PASSWORD_F:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                    break;
            }
        }
    };


}

