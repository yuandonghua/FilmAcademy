package starpark.filmacademy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import starpark.filmacademy.R;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.http.UserDataHttp;
import starpark.filmacademy.utils.MD5Util;
import starpark.filmacademy.utils.SharedPreferencesUtil;

/**
 * @description:登陆界面
 * @author:袁东华 created at 2016/8/22 0022 下午 3:27
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {


    //用户名输入框
    @ViewInject(R.id.phone_et)
    private EditText phone_et;
    //密码输入框
    @ViewInject(R.id.password_et)
    private EditText password_et;
    private View mProgressView;
    private View mLoginFormView;
    private TextView findPassword_tv;



    @Override
    public void initTopView(String title) {
        super.initTopView("登陆");
    }

    @Override
    public void initView() {


        mLoginFormView = findViewById(R.id.login_form);
    }

    @Override
    public void initListener() {
        //点击忘记密码
        findPassword_tv = (TextView) findViewById(R.id.findPassword_tv);
        findPassword_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,WritePhoneActivity.class);
                intent.putExtra(F,"忘记密码");
                startActivity(intent);
                finish();
            }
        });
        password_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mPhoneSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        //点击登陆
        mPhoneSignInButton.setOnClickListener(new OnClickListener() {
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

    String phone = "";
    String password = "";

    /**
     * @description:开始登陆
     * @author:袁东华 created at 2016/8/22 0022 下午 3:33
     */
    private void attemptLogin() {

        // Reset errors.
        phone_et.setError(null);
        password_et.setError(null);
        //获取手机号和密码
        phone = phone_et.getText().toString();
        password = password_et.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // 检查帐号是否为空
        if (TextUtils.isEmpty(phone)) {
            phone_et.setError(getString(R.string.error_no_null_required));
            focusView = phone_et;
            cancel = true;
        }
        //检查密码是否为空
        if (TextUtils.isEmpty(password)) {
            password_et.setError(getString(R.string.error_no_null_required));
            focusView = password_et;
            cancel = true;
        }
        if (!isPasswordValid(password)) {
            password_et.setError(getString(R.string.error_invalid_password));
            focusView = password_et;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            progressDialog.dismiss();
        } else {


            String pwdMD5String = MD5Util.getInstance().getMD5String(password);
            LogUtil.e("pwdMD5String:"+pwdMD5String);
            //提交登陆数据
            UserDataHttp.getInstance().login(phone, pwdMD5String, handler,
                    HttpIdentifyingCodeUtil.LOGIN_S, HttpIdentifyingCodeUtil.LOGIN_E);
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


    public Handler handler = new Handler() {
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //登陆成功
                case HttpIdentifyingCodeUtil.LOGIN_S:
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
                case HttpIdentifyingCodeUtil.LOGIN_E:
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_regist) {
            Intent intent = new Intent(activity, WritePhoneActivity.class);
            startActivity(intent);
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);

    }
}

