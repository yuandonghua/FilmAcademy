package starpark.filmacademy.utils;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


import org.xutils.image.ImageOptions;

import starpark.filmacademy.R;

import static android.widget.ImageView.ScaleType.*;

/**
 * @description:xutils框架
 * @author:袁东华 created at 2016/7/21 0021 下午 5:14
 */
public class XUtils {
    /**
     *@description:设置图片缩放类型
     *@author:袁东华
     *created at 2016/8/29 0029 下午 2:30
     */
    public ImageOptions getImageOptions(Activity activity, ImageView.ScaleType type) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.bg_loading)
                .setFailureDrawableId(R.drawable.bg_failure)
                .setImageScaleType(type)
                .build();
        return imageOptions;
    }
    /**
     * @description:默认缓存
     * @author:袁东华 created at 2016/8/23 0023 上午 10:30
     */
    public ImageOptions getImageOptions(Activity activity) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.bg_loading)
                .setFailureDrawableId(R.drawable.bg_failure)
                .build();
        return imageOptions;
    }

    /**
     * @description:是否缓存
     * @author:袁东华 created at 2016/8/23 0023 上午 10:30
     */
    public ImageOptions getImageOptions(Activity activity, boolean isCache) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setUseMemCache(isCache)
                .setLoadingDrawableId(R.drawable.bg_loading)
                .setFailureDrawableId(R.drawable.bg_failure)
                .build();
        return imageOptions;
    }

    /**
     * @description:圆角图片
     * @author:袁东华 created at 2016/8/23 0023 上午 10:30
     */
    public ImageOptions getImageOptions(Activity activity, int radius) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.bg_loading)
                .setFailureDrawableId(R.drawable.bg_failure)
                .setRadius(radius)
                .build();
        return imageOptions;
    }

    private static XUtils mInstance = null;

    private XUtils() {
    }

    public static XUtils getInstance() {
        if (mInstance == null) {
            synchronized (XUtils.class) {
                if (mInstance == null) {
                    mInstance = new XUtils();
                }
            }

        }
        return mInstance;
    }

}
