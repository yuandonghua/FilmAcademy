package starpark.filmacademy.utils;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 *@description: 存储的数据
 *@author:袁东华
 *created at 2016/7/4 0004 下午 5:43
 */
public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance;
    private static final String SHARED_PREFERENCES_NAME = "StarPark";
    /**
     * 保存用户的id
     *
     * @param activity
     * @param id  id
     */
    public void setId(Activity activity, String id) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("user_id", id);
        edit.commit();
    }

    /**
     * 获取用户的id
     * @param activity
     * @return id
     */
    public String getId(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_id", "");
    }
    /**
     * 保存用户的邮箱
     *
     * @param activity
     * @param email  邮箱
     */
    public void setEmail(Activity activity, String email) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("user_email", email);
        edit.commit();
    }

    /**
     * 获取用户的邮箱
     * @param activity
     * @return 邮箱
     */
    public String getEmail(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_email", "");
    }

    /**
     * 保存用户的昵称
     *
     * @param activity
     * @param name  昵称
     */
    public void setName(Activity activity, String name) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("user_name", name);
        edit.commit();
    }

    /**
     * 获取用户的昵称
     * @param activity
     * @return 昵称
     */
    public String getName(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_name", "");
    }
    /**
     * 保存用户的手机号
     *
     * @param activity
     * @param phone  手机号
     */
    public void setPhone(Activity activity, String phone) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("user_phone", phone);
        edit.commit();
    }

    /**
     * 获取用户的手机号
     * @param activity
     * @return 手机号
     */
    public String getPhone(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_phone", "");
    }
    /**
     * 保存用户的密码
     *
     * @param activity
     * @param password  密码
     */
    public void setPassword(Activity activity, String password) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("user_password", password);
        edit.commit();
    }

    /**
     * 获取用户的密码
     * @param activity
     * @return 密码
     */
    public String getPassword(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_password", "");
    }
    private SharedPreferencesUtil() {
    }

    public static SharedPreferencesUtil getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtil();
                }
            }
        }
        return instance;
    }
}
