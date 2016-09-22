package com.xingguangedu.myxg.utils;

import android.widget.Toast;

import org.xutils.common.util.LogUtil;

import com.xingguangedu.myxg.app.App;

/**
 * @description:全局异常处理
 * @author:袁东华
 * @time:2016/7/26 0026 下午 2:11:11
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (instance == null) {
            synchronized (CrashHandler.class) {
                if (instance == null) {
                    instance = new CrashHandler();
                    Thread.setDefaultUncaughtExceptionHandler(instance);
                }
            }
        }
        return instance;
    }

    /**
     * The thread is being terminated by an uncaught exception. Further
     * exceptions thrown in this method are prevent the remainder of the
     * method from executing, but are otherwise ignored.
     *
     * @param thread the thread that has an uncaught exception
     * @param ex     the exception that was thrown
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtil.e(ex.getMessage());
    }
}
