package starpark.filmacademy.page;

import android.app.Activity;
import android.view.View;

/**
 * @description:个人中心界面
 * @author:袁东华 created at 2016/8/24 0024 下午 3:54
 */
public class MePage {
    private static MePage instance;
    private View view = null;
    private Activity activity = null;


    private MePage() {


    }


    public void start(Activity activity, View view) {
        if (view != null && activity != null && (this.view == null && this.activity == null)) {
            this.activity = activity;
            this.view = view;
            initView();
            initData();
        }
    }

    public View getView() {
        return view;
    }

    private void initData() {

    }

    private void initView() {


    }

    public static MePage getInstance() {
        if (instance == null) {
            synchronized (MePage.class) {
                if (instance == null) {
                    instance = new MePage();
                }
            }
        }

        return instance;
    }
}
