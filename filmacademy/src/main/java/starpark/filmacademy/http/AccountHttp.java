package starpark.filmacademy.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;
/**
 *@description:与用户帐号有关的网络请求
 *@author:袁东华
 *created at 2016/8/22 0022 下午 4:22
 */
public class AccountHttp {


    /**
     * 修改手机号
     *
     * @param user_id 用户id
     * @param mobile  新手机号
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void modifyPhone(String user_id, String mobile, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.MIDIFY_PHONE);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("mobile", mobile);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        msg.what = success;
                    } else {
                        data.putString("message", message);
                        msg.what = error;

                    }
                    handler.sendMessage(msg);
                } catch (Exception e) {

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 修改密码
     *
     * @param user_id 用户id
     * @param pwd     旧密码
     * @param new_pwd 新密码
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void modifyPassword(String user_id, String pwd, String new_pwd, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.MIDIFY_PASSWORD);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("pwd", pwd);
        params.addBodyParameter("new_pwd", new_pwd);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        msg.what = success;
                    } else {
                        data.putString("message", message);
                        msg.what = error;

                    }
                    handler.sendMessage(msg);
                } catch (Exception e) {

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 注册帐号
     *
     * @param logname 用户名
     * @param pwd     密码
     * @param mobile  手机号
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void regist(String logname, String pwd, String mobile, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.REGIST);
        params.addBodyParameter("logname", logname);
        params.addBodyParameter("pwd", pwd);
        params.addBodyParameter("mobile", mobile);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                LogUtil.e("result:"+result.toString());
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        JSONObject info = result.getJSONObject("info");
                        JSONObject sacUser = info.getJSONObject("sacUser");
                        String id = sacUser.optString("id");
                        data.putString("id", id);
                        msg.what = success;
                    } else {
                        data.putString("message", message);
                        msg.what = error;

                    }
                    handler.sendMessage(msg);
                } catch (Exception e) {

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 登陆帐号
     *
     * @param mobile 手机号
     * @param pwd     密码
     * @param handler 接收结果
     * @param success 成功的标识
     * @param error   错误的标识
     */
    public void login(String mobile, String pwd, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.LOGIN);
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("pwd", pwd);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    LogUtil.e("result:" + result);
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        JSONObject info = result.getJSONObject("info");
                        JSONObject sacUser = info.getJSONObject("sacUser");

                        String email = sacUser.optString("email");
                        String mobile = sacUser.optString("mobile");
                        String id = sacUser.optString("id");
                        data.putString("email", email);
                        data.putString("mobile", mobile);
                        data.putString("id", id);
                        msg.what = success;
                    } else {
                        data.putString("message", message);
                        msg.what = error;

                    }
                    handler.sendMessage(msg);
                } catch (Exception e) {

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private static AccountHttp instance;

    private AccountHttp() {
    }

    public static AccountHttp getInstance() {
        if (instance == null) {
            synchronized (AccountHttp.class) {
                if (instance == null) {
                    instance = new AccountHttp();
                }
            }
        }
        return instance;
    }
}
