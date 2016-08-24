package starpark.filmacademy.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;

import starpark.filmacademy.R;

/**
 * @description:填写手机号界面
 * @author:袁东华 created at 2016/8/22 0022 下午 3:59
 */
@ContentView(R.layout.activity_regist)
public class WritePhoneActivity extends BaseActivity {


    private static final int REQUEST_READ_CONTACTS = 0;


    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "18801123830:123456", "bar@example.com:world"
    };

    // UI references.
    private AutoCompleteTextView mPhoneView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView findPassword_tv;
    private String flag;

    @Override
    public void receiveIntentData() {
        flag = getIntent().getStringExtra("flag");
    }

    @Override
    public void initTopView(String title) {
        super.initTopView("填写手机号");

    }

    @Override
    public void initView() {

        mPhoneView = (AutoCompleteTextView) findViewById(R.id.phone_tv);


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
     * @description:把手机号传递到下一层
     * @author:袁东华 created at 2016/7/21 0021 下午 4:17
     */
    private void attemptLogin() {

        // Reset errors.
        mPhoneView.setError(null);
        //获取手机号和密码
        String phone = mPhoneView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // 检查手机号是否为空
        if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError(getString(R.string.error_no_null_required));
            focusView = mPhoneView;
            cancel = true;
        } else if (!isPhoneValid(phone)) {
            //检查手机号是否有效
            mPhoneView.setError(getString(R.string.error_invalid_required));
            focusView = mPhoneView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            Intent intent = new Intent(activity, WriteIdentifyingCodeActivity.class);
            intent.putExtra(F, flag);
            intent.putExtra("phone", phone);
            startActivity(intent);
            finish();
        }
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.startsWith("1");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * 显示隐藏进度条
     *
     * @param show
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}

