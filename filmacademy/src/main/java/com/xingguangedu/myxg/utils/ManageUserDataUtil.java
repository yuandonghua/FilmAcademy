package com.xingguangedu.myxg.utils;

import android.app.Activity;
import android.util.Log;

import org.xutils.common.util.LogUtil;

/**
 * @description:管理用户的一些数据
 * @author:袁东华 created at 2016/7/4 0004 下午 5:53
 */
public class ManageUserDataUtil {
    //设备号
    private String  deviceCode;

    public String getDeviceCode() {
        LogUtil.e("deviceCode:"+deviceCode);
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public boolean getHasAlias() {
        LogUtil.e("hasAlias:"+hasAlias);
        return hasAlias;
    }

    public void setHasAlias(boolean hasAlias) {
        this.hasAlias = hasAlias;
    }
    //是否设置别名
    private boolean hasAlias;

    public void setUserName(Activity activity, String name) {
        userName = name;
        SharedPreferencesUtil.getInstance().setName(activity, userName);
    }
    private String userSchool="";
    public void setUserSchool(Activity activity, String school) {
        userSchool = school;
        SharedPreferencesUtil.getInstance().setSchool(activity, userName);
    }
    public String getUserSchool(Activity activity) {

        if ("".equals(userSchool)) {
            userSchool = SharedPreferencesUtil.getInstance().getSchool(activity);
        }

        return userSchool;
    }
    /**
     * @description:是否是第一次进入app
     * @author:袁东华 created at 2016/8/29 0029 上午 11:00
     */
    public boolean isFirst(Activity activity) {

        return SharedPreferencesUtil.getInstance().getIsFirst(activity);
    }

    /**
     * @description:退出登陆
     * @author:袁东华 created at 2016/8/29 0029 上午 10:26
     */
    public void logOff(Activity activity) {

        clearUserInfo(activity);
    }

    private String userId = "";
    private String userName = "";
    private String userPhone = "";
    private String userPassword = "";

    public void setUserPassword(Activity activity, String password) {
        userPassword = password;
        SharedPreferencesUtil.getInstance().setPassword(activity, userPassword);
    }

    public String getUserPassword(Activity activity) {

        if ("".equals(userPassword)) {
            userPassword = SharedPreferencesUtil.getInstance().getPassword(activity);
        }

        return userPassword;
    }

    public String getUserPhone(Activity activity) {

        if ("".equals(userPhone)) {
            userPhone = SharedPreferencesUtil.getInstance().getPhone(activity);
        }

        return userPhone;
    }

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
        if (!"".equals(getUserId(activity)) && !"".equals(getUserName(activity)) && !"".equals(getUserPassword(activity))) {
            return true;
        }
        return false;
    }

    /**
     * @description:清除用户数据
     * @author:袁东华 created at 2016/8/29 0029 上午 10:26
     */
    public boolean clearUserInfo(Activity activity) {
        SharedPreferencesUtil.getInstance().clearData(activity);
        userId = "";
        userName = "";
        userPassword = "";
        userPhone = "";
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
