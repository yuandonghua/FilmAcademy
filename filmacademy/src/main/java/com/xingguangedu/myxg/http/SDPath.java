package com.xingguangedu.myxg.http;

import android.os.Environment;

/**
 * @description:本地路径
 * @author:袁东华
 * @time:2016/7/20 0020 上午 11:09:09
 */
public class SDPath {
    //APP存储根目录
    public static final String APP = Environment.getExternalStorageDirectory().getAbsolutePath() + "/weibao";
    //图片跟路径
    public static final String IMAGE=APP+"/image";
    //图片处理过程中缓存图片的路劲
    public static final String IMAGE_CACHE=IMAGE+"/cache";
    //下载路径
    public static final String DOWNLOAD=Environment.getExternalStorageDirectory().getAbsolutePath()+"/童星电影学院";



}
