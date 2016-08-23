package starpark.filmacademy.utils;

import android.app.Activity;

/**
 * @description:管理用户的一些数据
 * @author:袁东华 created at 2016/7/4 0004 下午 5:53
 */
public class ManageUserDataUtil {
    private String userId = "";
    private String userName = "";

    public String getUserName(Activity activity) {

        if ("".equals(userName)) {
            userName = SharedPreferencesUtil.getInstance().getName(activity);
        }

        return userName;
    }

    public String getUserId(Activity activity) {

        if ("".equals(userId)) {
            userId = SharedPreferencesUtil.getInstance().getId(activity);
        }

        return userId;
    }

    /**
     * @description:判断用户是否登录
     * @author:袁东华 created at 2016/7/12 0012 上午 9:39
     */
    public boolean isLogin(Activity activity) {
        String id = SharedPreferencesUtil.getInstance().getId(activity);
        String name = SharedPreferencesUtil.getInstance().getName(activity);
        String password = SharedPreferencesUtil.getInstance().getPassword(activity);
        if (!"".equals(id) && !"".equals(name) && !"".equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * 清除用户的数据
     *
     * @return
     */
    public boolean clearUserInfo(Activity activity) {
        userId = "";
        SharedPreferencesUtil.getInstance().setId(activity, "");
        SharedPreferencesUtil.getInstance().setName(activity, "");
        SharedPreferencesUtil.getInstance().setPassword(activity, "");
        SharedPreferencesUtil.getInstance().setPhone(activity, "");
        SharedPreferencesUtil.getInstance().setEmail(activity, "");
        return true;
    }

    private static ManageUserDataUtil instance;

    private ManageUserDataUtil() {
    }

    public static ManageUserDataUtil getInstance() {
        if (instance == null) {
            synchronized (ManageUserDataUtil.class) {
                if (instance == null) {
                    instance = new ManageUserDataUtil();
                }
            }
        }
        return instance;
    }


}
