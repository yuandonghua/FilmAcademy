package starpark.filmacademy.activity;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;

import starpark.filmacademy.R;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.utils.SharedPreferencesUtil;

/**
 * @description:填写验证码界面
 * @author:袁东华 created at 2016/8/22 0022 下午 4:02
 */
@ContentView(R.layout.activity_identifyingcode)
public class WriteIdentifyingCodeActivity extends BaseActivity {


    // UI references.
    private EditText identifyingCode_tv;
    private View mProgressView;
    private View mLoginFormView;
    private Activity activity = WriteIdentifyingCodeActivity.this;
    private TextView findPassword_tv;
    private String identifyingCode = "8888";
    private String flag;
    private String phone;

    @Override
    public void receiveIntentData() {
        flag = getIntent().getStringExtra(F);
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    public void initTopView(String title) {
        super.initTopView("填写验证码");

    }

    @Override
    public void initView() {
        identifyingCode_tv = (EditText) findViewById(R.id.identifyingCode_tv);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public void initListener() {
        Button next_btn = (Button) findViewById(R.id.next_btn);


        //点击下一步
        next_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * @description:验证验证码,把数据传递到下一层
     * @author:袁东华 created at 2016/7/21 0021 下午 4:18
     */
    private void attemptLogin() {
        // Reset errors.
        identifyingCode_tv.setError(null);

        String code = identifyingCode_tv.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(code)) {
            identifyingCode_tv.setError(getString(R.string.error_no_null_required));
            focusView = identifyingCode_tv;
            cancel = true;
        } else if (!identifyingCode.equals(code)) {
            identifyingCode_tv.setError(getString(R.string.error_invalid_required));
            focusView = identifyingCode_tv;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            if ("修改密码".equals(flag) || "忘记密码".equals(flag)) {
                Intent intent = new Intent(activity, SetPasswordActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra(F, flag);
                startActivity(intent);
                finish();
            } else {
                //注册流程
                //去填写昵称和密码
                Intent intent = new Intent(activity, SetNameWithPasswordActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra(F, flag);
                startActivity(intent);
                finish();
            }
        }
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.startsWith(identifyingCode);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
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
//            switch (msg.what) {
//                //手机号修改成功
//                case HttpIdentifyingCodeUtil.MODIFY_PHONE_S:
//                    Bundle data = msg.getData();
//                    if (data != null) {
//                        SharedPreferencesUtil.getInstance().setPhone(activity, phone);
//                        Toast.makeText(activity, R.string.modify_phone_success, Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//
//                    break;
//                //手机号修改失败
//                case HttpIdentifyingCodeUtil.MODIFY_PHONE_F:
//                    Bundle errorData = msg.getData();
//                    if (errorData != null) {
//                        String message = errorData.getString("message");
//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//            }
        }
    };
}

