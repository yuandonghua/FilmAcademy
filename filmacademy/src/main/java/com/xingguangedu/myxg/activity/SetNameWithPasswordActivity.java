package com.xingguangedu.myxg.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;

import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.http.HttpIdentifyingCodeUtil;
import com.xingguangedu.myxg.http.UserDataHttp;
import com.xingguangedu.myxg.utils.MD5Util;
import com.xingguangedu.myxg.utils.SharedPreferencesUtil;


/**
 * @description:设置用户昵称和密码
 * @author:袁东华 created at 2016/8/22 0022 下午 4:05
 */
@ContentView(R.layout.activity_set_name_with_password)
public class SetNameWithPasswordActivity extends BaseActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "18801123830:123456", "bar@example.com:world"
    };

    /**
     * 用户名输入框
     */
    private EditText name_et;
    /**
     * 密码输入框
     */
    private EditText password_et;
    /**
     * 确认密码输入框
     */
    private EditText confirmPassword_et;
    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = SetNameWithPasswordActivity.this;
    private TextView findPassword_tv;


    private String phone;
    private String flag;
    private String name;
    private String password;

    @Override
    public void receiveIntentData() {
        phone = getIntent().getStringExtra("phone");
        flag = getIntent().getStringExtra(F);
    }

    @Override
    public void initTopView(String title) {
        super.initTopView("填写昵称和密码");

    }

    @Override
    public void initView() {

        name_et = (EditText) findViewById(R.id.name_et);
        password_et = (EditText) findViewById(R.id.password_et);
        confirmPassword_et = (EditText) findViewById(R.id.confirmPassword_et);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public void initListener() {
        Button submit_btn = (Button) findViewById(R.id.submit_btn);
        //点击下一步
        submit_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                attemptLogin();
            }
        });
    }

    @Override
    public void initData() {

    }


    /**
     * @description:记录数据,传递到下一层
     * @author:袁东华 created at 2016/7/21 0021 下午 4:22
     */
    private void attemptLogin() {

        // Reset errors.
        name_et.setError(null);
        password_et.setError(null);
        confirmPassword_et.setError(null);

        //获取用户名,密码,确认密码
        name = name_et.getText().toString();
        password = password_et.getText().toString();
        String confirmPassword = confirmPassword_et.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // 检查用户名是否为空
        if (TextUtils.isEmpty(name)) {
            name_et.setError(getString(R.string.error_no_null_required));
            focusView = name_et;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
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
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            progressDialog.dismiss();
        } else {
            String md5String = MD5Util.getInstance().getMD5String(password);
            //提交注册信息
            UserDataHttp.getInstance().regist(name, md5String, phone, handler,
                    HttpIdentifyingCodeUtil.REGIST_S, HttpIdentifyingCodeUtil.REGIST_E);
        }
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.startsWith("1");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 4;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //注册成功
                case HttpIdentifyingCodeUtil.REGIST_S:
                    Bundle data = msg.getData();
                    if (data != null) {

                        String id = data.getString("id");
                        SharedPreferencesUtil.getInstance().setId(activity, id);
                        SharedPreferencesUtil.getInstance().setPhone(activity, phone);
                        SharedPreferencesUtil.getInstance().setName(activity, name);
                        SharedPreferencesUtil.getInstance().setPassword(activity, password);

                        Toast.makeText(activity, R.string.regist_success, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(activity,PersonalDatumActivity.class));
                        finish();
                    }

                    break;
                //注册失败
                case HttpIdentifyingCodeUtil.REGIST_E:
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

