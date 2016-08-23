package starpark.filmacademy.page;

import android.app.Activity;
import android.view.View;

/**
 * 蜂巢页面
 */

public class DownloadPage {
    private static DownloadPage instance;
    private View view = null;
    private Activity activity = null;



    private DownloadPage() {


    }

    /**
     * 开始加载蜂巢界面
     */
    public void start(Activity activity, View view) {
        if (view != null && activity != null && (this.view == null && this.activity == null)) {
            this.activity = activity;
            this.view = view;
            initView();
            initData();
        }
    }
    public View getView(){
        return view;
    }

    private void initData() {

    }

    private void initView() {



    }

    public static DownloadPage getInstance() {
        if (instance == null) {
            synchronized (DownloadPage.class) {
                if (instance == null) {
                    instance = new DownloadPage();
                }
            }
        }

        return instance;
    }
}
