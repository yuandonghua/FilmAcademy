package starpark.filmacademy.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import starpark.filmacademy.data.Film;
import starpark.filmacademy.data.PersonalDatum;

/**
 * @description:与用户帐号有关的网络请求
 * @author:袁东华 created at 2016/8/22 0022 下午 4:22
 */
public class UserDataHttp {

    /**
     * 忘记密码
     *
     * @param new_pwd 新密码
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void forgetPassword(String mobile, String new_pwd, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.FORGET_PASSWORD);
        params.addBodyParameter("mobile", mobile);
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
                        JSONObject info = result.getJSONObject("info");
                        JSONObject sacUser = info.getJSONObject("sacUser");

                        String email = sacUser.optString("email");
                        String mobile = sacUser.optString("mobile");
                        String logname = sacUser.optString("logname");
                        String id = sacUser.optString("id");

                        String role = sacUser.optString("role");
                        data.putString("email", email);
                        data.putString("mobile", mobile);
                        data.putString("logname", logname);
                        data.putString("role", role);
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
     * 用户反馈
     *
     * @param user_id
     * @param words
     * @param handler
     * @param success
     * @param error
     */
    public void sendFeedback(String user_id, String words, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.SACCOMMENTWT);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("words", words);
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
     * 获取个人资料
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getSacUserInfoGet(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.SACUSERINFOGET);
        params.addBodyParameter("user_id", user_id);
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
                        if (!result.isNull("info")) {
                            JSONObject info = result.getJSONObject("info");

                            PersonalDatum personalDatum = new PersonalDatum();
                            personalDatum.setId(info.optString("id"));
                            personalDatum.setName(info.optString("logname"));
                            personalDatum.setPhone(info.optString("mobile"));
                            personalDatum.setRole(info.optString("role"));
                            personalDatum.setSchool(info.optString("school"));
                            personalDatum.setGrade(info.optString("grade"));
                            personalDatum.setSpecialty(info.optString("specialty"));
                            personalDatum.setTrain(info.optString("train"));
                            personalDatum.setAward(info.optString("award"));
                            data.putParcelable("personalDatum", personalDatum);


                        }

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
     * 修改用户资料
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void modifyPersonalDatum(String user_id, String logname,
                                    String school, String grade, String specialty, String train, String award,
                                    final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.SACUSERINFOUPDWT);
        //用户id
        params.addBodyParameter("user_id", user_id);
        //用户名
        params.addBodyParameter("logname", logname);
        //学校
        params.addBodyParameter("school", school);
        //班级
        params.addBodyParameter("grade", grade);
        //特长
        params.addBodyParameter("specialty", specialty);
        //培训
        params.addBodyParameter("train", train);
        //证书
        params.addBodyParameter("award", award);
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
                        JSONObject info = result.getJSONObject("info");
                        JSONObject sacUser = info.getJSONObject("sacUser");

                        String email = sacUser.optString("email");
                        String mobile = sacUser.optString("mobile");
                        String logname = sacUser.optString("logname");
                        String id = sacUser.optString("id");

                        String role = sacUser.optString("role");
                        data.putString("email", email);
                        data.putString("mobile", mobile);
                        data.putString("logname", logname);
                        data.putString("role", role);
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
                LogUtil.e("result:" + result.toString());
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
     * @param mobile  手机号
     * @param pwd     密码
     * @param handler 接收结果
     * @param success 成功的标识
     * @param error   错误的标识
     */
    public void login(String mobile, String pwd, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.LOGIN);
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("pwd", pwd);
        LogUtil.d("");
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        if (!result.isNull("info")){
                            JSONObject info = result.getJSONObject("info");
                            if (!info.isNull("sacUser")){
                                JSONObject sacUser = info.getJSONObject("sacUser");

                                PersonalDatum personalDatum = new PersonalDatum();
                                personalDatum.setId(sacUser.optString("id"));
                                personalDatum.setName(sacUser.optString("logname"));
                                personalDatum.setPhone(sacUser.optString("mobile"));
                                personalDatum.setRole(sacUser.optString("role"));
                                personalDatum.setSchool(sacUser.optString("school"));
                                personalDatum.setGrade(sacUser.optString("grade"));
                                personalDatum.setSpecialty(sacUser.optString("specialty"));
                                personalDatum.setTrain(sacUser.optString("train"));
                                personalDatum.setAward(sacUser.optString("award"));
                                personalDatum.setPassword(sacUser.optString("pwd"));

                                data.putParcelable("personalDatum", personalDatum);

                                msg.what = success;
                            }
                        }

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

    private static UserDataHttp instance;

    private UserDataHttp() {
    }

    public static UserDataHttp getInstance() {
        if (instance == null) {
            synchronized (UserDataHttp.class) {
                if (instance == null) {
                    instance = new UserDataHttp();
                }
            }
        }
        return instance;
    }
}
