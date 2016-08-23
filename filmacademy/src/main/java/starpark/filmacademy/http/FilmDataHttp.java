package starpark.filmacademy.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import starpark.filmacademy.data.Film;

/**
 * @description:与电影资源有关的网络请求
 * @author:袁东华 created at 2016/8/22 0022 下午 5:08
 */
public class FilmDataHttp {
    /**
     * 获取课程列表
     *
     * @param course_id 课程id
     * @param handler   接收结果
     * @param success   成功标识
     * @param error     错误标识
     */
    public void getResCourseDetail(String course_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RESCOURSEDETAIL);
        params.addBodyParameter("course_id", course_id);
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
                            ArrayList<Film> list=new ArrayList<Film>();
                            if (!info.isNull("course")) {
                                JSONObject course = info.getJSONObject("course");

                                Film film = new Film();
                                film.setId(course.optString("id"));
                                film.setTitle(course.optString("title"));
                                film.setDescr(course.optString("descr"));
                                film.setUrl(course.optString("url"));
                                film.setAddTimeShow(course.optString("addTimeShow"));
                                film.setTimeShow(course.optString("timeShow"));
                                film.setSort(course.optString("sort"));
                                film.setThumb(HttpUrlUtil.SERVER_IMAGE + course.optString("thumb"));
                                film.setSfrom(course.optString("sfrom"));
                                film.setDuration(course.optString("duration"));
                                film.setAuth(course.optString("auth"));

                                list.add(0,film);
                            }
                            if (!info.isNull("others")) {
                                JSONArray others = info.getJSONArray("others");
                                for (int i = 0; i < others.length(); i++) {
                                    JSONObject obji=others.getJSONObject(i);
                                    Film film = new Film();
                                    film.setId(obji.optString("id"));
                                    film.setTitle(obji.optString("title"));
                                    film.setDescr(obji.optString("descr"));
                                    film.setUrl(obji.optString("url"));
                                    film.setAddTimeShow(obji.optString("addTimeShow"));
                                    film.setTimeShow(obji.optString("timeShow"));
                                    film.setSort(obji.optString("sort"));
                                    film.setThumb(HttpUrlUtil.SERVER_IMAGE + obji.optString("thumb"));
                                    film.setSfrom(obji.optString("sfrom"));
                                    film.setDuration(obji.optString("duration"));
                                    film.setAuth(obji.optString("auth"));

                                    list.add(film);
                                }

                            }
                            data.putParcelableArrayList("list",list);
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
     * 获取课程列表
     *
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getResGroupList(final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RESGROUPLIST);
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
                            JSONArray list = result.getJSONArray("info");
                            ArrayList<Film> filmList = new ArrayList<Film>();

                            for (int i = 0; i < list.length(); i++) {
                                JSONObject obji = list.getJSONObject(i);
                                Film film = new Film();
                                film.setId(obji.optString("id"));
                                film.setTitle(obji.optString("title"));
                                film.setDescr(obji.optString("descr"));
                                film.setAddTimeShow(obji.optString("totalTime"));
                                film.setCount(obji.optString("cnt"));
                                film.setThumb(HttpUrlUtil.SERVER_IMAGE + obji.optString("titlePic"));

                                filmList.add(film);
                            }
                            data.putParcelableArrayList("list", filmList);
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
     * 获取课程列表
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getResCourseList(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RESCOURSELIST);
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
                            if (!info.isNull("list")) {
                                JSONArray list = info.getJSONArray("list");
                                ArrayList<Film> filmList = new ArrayList<Film>();

                                for (int i = 0; i < list.length(); i++) {
                                    JSONObject obji = list.getJSONObject(i);
                                    Film film = new Film();
                                    film.setId(obji.optString("id"));
                                    film.setTitle(obji.optString("title"));
                                    film.setDescr(obji.optString("descr"));
                                    film.setUrl(obji.optString("url"));
                                    film.setAddTimeShow(obji.optString("addTimeShow"));
                                    film.setSort(obji.optString("sort"));
                                    film.setThumb(HttpUrlUtil.SERVER_IMAGE + obji.optString("thumb"));
                                    film.setSfrom(obji.optString("sfrom"));
                                    film.setDuration(obji.optString("duration"));
                                    film.setAuth(obji.optString("auth"));
                                    filmList.add(film);
                                }
                                data.putParcelableArrayList("list", filmList);
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


    private static FilmDataHttp instance;

    private FilmDataHttp() {
    }

    public static FilmDataHttp getInstance() {
        if (instance == null) {
            synchronized (FilmDataHttp.class) {
                if (instance == null) {
                    instance = new FilmDataHttp();
                }
            }
        }
        return instance;
    }
}
