package com.xingguangedu.myxg.view.tab;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by Administrator on 2016/6/22 0022.
 */

public class ViewUtilsLollipop {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static void setBoundsViewOutlineProvider(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }
}
