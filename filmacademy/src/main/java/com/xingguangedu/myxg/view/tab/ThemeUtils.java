package com.xingguangedu.myxg.view.tab;
import android.content.Context;
import android.content.res.TypedArray;

import com.xingguangedu.myxg.R;


/**
 * Created by Administrator on 2016/6/22 0022.
 */

public class ThemeUtils {
    private static final int[] APPCOMPAT_CHECK_ATTRS = { R.attr.colorPrimary };

    static void checkAppCompatTheme(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        final boolean failed = !a.hasValue(0);
        if (a != null) {
            a.recycle();
        }
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme "
                    + "(or descendant) with the design library.");
        }
    }
}
