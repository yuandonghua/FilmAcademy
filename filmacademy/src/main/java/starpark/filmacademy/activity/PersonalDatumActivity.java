package starpark.filmacademy.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;


import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import starpark.filmacademy.R;
import starpark.filmacademy.data.PersonalDatum;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.http.UserDataHttp;
import starpark.filmacademy.utils.InputMethodUtil;
import starpark.filmacademy.utils.ManageUserDataUtil;

/**
 * @description:个人资料界面
 * @author:袁东华 created at 2016/8/26 0026 下午 12:03
 */
@ContentView(R.layout.activity_personaldatum)
public class PersonalDatumActivity extends BaseActivity {
    @ViewInject(R.id.name_til)
    private TextInputLayout name_til;
    @ViewInject(R.id.name_et)
    private EditText name_et;
    @ViewInject(R.id.school_et)
    private EditText school_et;
    @ViewInject(R.id.class_et)
    private EditText class_et;
    @ViewInject(R.id.speciality_et)
    private EditText speciality_et;
    @ViewInject(R.id.train_et)
    private EditText train_et;
    @ViewInject(R.id.certificate_et)
    private EditText certificate_et;
    @ViewInject(R.id.save_btn)
    private Button save_btn;
    @ViewInject(R.id.login_form)
    private ScrollView login_form;

    @Override
    public void initTopView(String title) {
        super.initTopView("个人资料");
    }


    @Override
    public void initListener() {


        //点击保存
        save_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = save_btn.getText().toString();
                if (name.equals("保存")) {
                    attemptSubmit();
                } else if (name.equals("编辑")) {
                    attemptEdit();
                }
            }
        });

    }

    /**
     * @description:编辑资料
     * @author:袁东华 created at 2016/8/26 0026 下午 2:01
     */
    private void attemptEdit() {
        save_btn.setText("保存");
        clearFocus(false);
    }

    @Override
    public void initData() {
        progressDialog.show();
        UserDataHttp.getInstance().getSacUserInfoGet(ManageUserDataUtil.getInstance().getUserId(activity),
                handler, HttpIdentifyingCodeUtil.SACUSERINFOGET_S, HttpIdentifyingCodeUtil.SACUSERINFOGET_E);

    }


    /**
     * @description:提交数据
     * @author:袁东华 created at 2016/7/20 0020 下午 5:47
     */
    private void attemptSubmit() {
        String name = name_et.getText().toString();
        String school = school_et.getText().toString();
        String clas = class_et.getText().toString();
        String speciality = speciality_et.getText().toString();
        String train = train_et.getText().toString();
        String certificate = certificate_et.getText().toString();

        progressDialog.show();
        //修改个人资料
        UserDataHttp.getInstance().modifyPersonalDatum(ManageUserDataUtil.getInstance().getUserId(activity),
                name, school, clas, speciality, train, certificate,
                handler, HttpIdentifyingCodeUtil.SACUSERINFOUPDWT_S, HttpIdentifyingCodeUtil.SACUSERINFOUPDWT_E);

    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                //获取个人资料成功
                case HttpIdentifyingCodeUtil.SACUSERINFOGET_S:
                    Bundle data1 = msg.getData();
                    if (data1 != null) {
                        PersonalDatum personalDatum = data1.getParcelable("personalDatum");
                        if (personalDatum != null) {
                            name_et.setText(personalDatum.getName());
                            school_et.setText(personalDatum.getSchool());
                            class_et.setText(personalDatum.getGrade());
                            speciality_et.setText(personalDatum.getSpecialty());
                            train_et.setText(personalDatum.getTrain());
                            certificate_et.setText(personalDatum.getAward());


                        }
                    }
                    progressDialog.dismiss();
                    break;
                //获取个人资料失败
                case HttpIdentifyingCodeUtil.SACUSERINFOGET_E:
                    Bundle errorData2 = msg.getData();
                    if (errorData2 != null) {
                        String message = errorData2.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();

                    break;
                //修改个人资料成功
                case HttpIdentifyingCodeUtil.SACUSERINFOUPDWT_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        Toast.makeText(activity, "修改资料成功", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();

                    break;
                //修改个人资料失败
                case HttpIdentifyingCodeUtil.SACUSERINFOUPDWT_E:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                    break;
            }
        }
    };

    private void clearFocus(boolean isClear) {
        if (isClear) {
            Toast.makeText(activity, "失去焦点", Toast.LENGTH_SHORT).show();
            LogUtil.e("失去焦点");
            name_et.setFocusable(false);
            school_et.setFocusable(false);
            class_et.setFocusable(false);
            speciality_et.setFocusable(false);
            train_et.setFocusable(false);
            certificate_et.setFocusable(false);

        } else {
            name_et.setFocusable(true);
            name_et.requestFocus();
            school_et.setFocusable(true);
            class_et.setFocusable(true);
            speciality_et.setFocusable(true);
            train_et.setFocusable(true);
            certificate_et.setFocusable(true);
        }

    }
}

