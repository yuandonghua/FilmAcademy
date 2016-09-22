package com.xingguangedu.myxg.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.http.HttpIdentifyingCodeUtil;
import com.xingguangedu.myxg.http.UserDataHttp;
import com.xingguangedu.myxg.utils.ManageUserDataUtil;

/**
 *@description:用户反馈
 *@author:袁东华
 *created at 2016/8/29 0029 上午 9:39
 */
@ContentView(R.layout.activity_feedback)
public class FeedbackActivity extends BaseActivity {
    @ViewInject(R.id.feedback_et)
    private EditText feedback_et;
    private String feedback = "";

    @Override
    public void initTopView(String title) {
        super.initTopView("反馈");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_send) {
            attemptSend();


            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void attemptSend() {
        progressDialog.show();
        feedback_et.setError(null);
        feedback = feedback_et.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // 检查帐号是否为空
        if (TextUtils.isEmpty(feedback)) {
            feedback_et.setError(getString(R.string.error_no_null_required));
            focusView = feedback_et;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            progressDialog.dismiss();
        } else {
            UserDataHttp.getInstance().sendFeedback(ManageUserDataUtil.getInstance().getUserId(activity),
                    feedback,handler,HttpIdentifyingCodeUtil.REGIST_S,HttpIdentifyingCodeUtil.REGIST_E);

        }

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
                case HttpIdentifyingCodeUtil.REGIST_S:
                    Bundle data = msg.getData();
                    if (data != null) {

                        progressDialog.dismiss();
                        finish();
                    }

                    break;
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
