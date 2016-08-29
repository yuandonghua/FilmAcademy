package starpark.filmacademy.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import starpark.filmacademy.R;
import starpark.filmacademy.utils.ManageUserDataUtil;

/**
 * @description:设置界面
 * @author:袁东华 created at 2016/8/26 0026 上午 10:37
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    @ViewInject(R.id.phone_tv)
    private TextView phone_tv;

    @Override
    public void initTopView(String title) {
        super.initTopView("设置");
    }

    @Override
    public void initData() {
        String phone = ManageUserDataUtil.getInstance().getUserPhone(activity);
        if (phone != "") {
            phone = phone.replace(phone.substring(3, 7), "****");
            phone_tv.setText(phone);
        }
    }

    @Override
    public void initListener() {
        //点击个人资料
        TextView personalDatum_tv = (TextView) findViewById(R.id.personalDatum_tv);
        personalDatum_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, PersonalDatumActivity.class));
            }
        });
        //点击修改密码
        TextView modifyPasswd_tv = (TextView) findViewById(R.id.modifyPasswd_tv);
        modifyPasswd_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, WriteIdentifyingCodeActivity.class);
                intent.putExtra(F,"修改密码");
                startActivity(intent);
            }
        });
        //点击退出登陆
        Button logoff_btn = (Button) findViewById(R.id.logoff_btn);
        logoff_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageUserDataUtil.getInstance().logOff(activity);
                finish();
            }
        });

    }
}
