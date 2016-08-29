package starpark.filmacademy.http;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;

import starpark.filmacademy.activity.MainActivity;
import starpark.filmacademy.app.App;
import starpark.filmacademy.data.Film;

/**
 * @description:与电影资源有关的网络请求
 * @author:袁东华 created at 2016/8/22 0022 下午 5:08
 */
public class FilmDataHttp {

    public void downFilmResource(String saveFilePath, String url, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(url);
        params.setSaveFilePath(saveFilePath);
        x.http().get(params, new Callback.CommonCallback<File>() {

            @Override
            public void onSuccess(File file) {

                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    if (file != null && file.exists()) {
                        msg.what = success;

                    } else {
                        data.putString("message", "下载失败");
                        msg.what = error;

                    }
                    handler.sendMessage(msg);
                } catch (Exception e) {
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Message msg = new Message();
                Bundle data = new Bundle();
                msg.setData(data);

                data.putString("message", ex.getMessage());
                msg.what = error;

                handler.sendMessage(msg);
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
     * 获取播放记录列表
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getResHistoryList(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RESHISTORYLIST);
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
                            JSONArray info = result.getJSONArray("info");
                            ArrayList<Film> filmList = new ArrayList<Film>();

                            for (int i = 0; i < info.length(); i++) {
                                JSONObject obji = info.getJSONObject(i);
                                if (!obji.isNull("resCourse")) {
                                    JSONObject resCourse = obji.getJSONObject("resCourse");

                                    Film film = new Film();
                                    film.setId(resCourse.optString("id"));
                                    film.setTitle(resCourse.optString("title"));
                                    film.setDescr(resCourse.optString("descr"));
                                    film.setUrl(resCourse.optString("url"));
                                    film.setAddTimeShow(resCourse.optString("addTimeShow"));
                                    film.setSort(resCourse.optString("sort"));
                                    film.setThumb(HttpUrlUtil.SERVER_IMAGE + resCourse.optString("thumb"));
                                    film.setSfrom(HttpUrlUtil.SERVER_IMAGE + resCourse.optString("sfrom"));
                                    film.setTimeShow(resCourse.optString("timeShow"));
                                    film.setAuth(resCourse.optString("auth"));
                                    film.setCanShow(resCourse.optString("canShow"));
                                    film.setHasFavourite(resCourse.optString("hasFavourite"));
                                    filmList.add(film);
                                }
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
     * 添加播放历史
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getRecCoursePlayHisWt(String user_id, String course_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RECCOURSEPLAYHISWT);
        params.addBodyParameter("user_id", user_id);
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
     * 取消收藏
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getRecCourseFavoriteDelWt(String user_id, String course_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RECCOURSEFAVORITEDELWT);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("course_id", course_id);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                LogUtil.e("result:" + result);
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
     * 添加收藏
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getRecCourseFavoriteAddWt(String user_id, String course_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RECCOURSEFAVORITEADDWT);
        params.addBodyParameter("user_id", user_id);
        params.addBodyParameter("course_id", course_id);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject result) {
                LogUtil.e("result:" + result);
                try {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.setData(data);
                    String message = result.optString("message");
                    if ("success".equals(message)) {
                        msg.what = success;


                    } else {

                        msg.what = error;

                    }
                    data.putString("message", message);
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
     * 获取收藏列表
     *
     * @param user_id 用户id
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getSacUserFavouriteList(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.SACUSERFAVOURITELIST);
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
                            JSONArray info = result.getJSONArray("info");
                            ArrayList<Film> filmList = new ArrayList<Film>();

                            for (int i = 0; i < info.length(); i++) {
                                JSONObject obji = info.getJSONObject(i);
                                if (!obji.isNull("resCourse")) {
                                    JSONObject resCourse = obji.getJSONObject("resCourse");

                                    Film film = new Film();
                                    film.setId(resCourse.optString("id"));
                                    film.setTitle(resCourse.optString("title"));
                                    film.setDescr(resCourse.optString("descr"));
                                    film.setUrl(resCourse.optString("url"));
                                    film.setAddTimeShow(resCourse.optString("addTimeShow"));
                                    film.setSort(resCourse.optString("sort"));
                                    film.setThumb(HttpUrlUtil.SERVER_IMAGE + resCourse.optString("thumb"));
                                    film.setSfrom(HttpUrlUtil.SERVER_IMAGE + resCourse.optString("sfrom"));
                                    film.setTimeShow(resCourse.optString("timeShow"));
                                    film.setAuth(resCourse.optString("auth"));
                                    film.setCanShow(resCourse.optString("canShow"));
                                    film.setHasFavourite(resCourse.optString("hasFavourite"));
                                    filmList.add(film);
                                }
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
    public void getResRepositist(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RESREPOSITIST);
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
                                    film.setTitle(obji.optString("name"));
                                    film.setDescr(obji.optString("descr"));
                                    film.setUrl(obji.optString("url"));
                                    film.setAddTimeShow(obji.optString("addTimeShow"));
                                    film.setSort(obji.optString("sort"));
                                    film.setPath(HttpUrlUtil.SERVER_IMAGE + obji.optString("path"));
                                    //判断文件是否缓存
                                    String path = film.getPath();
                                    String suffix = path.substring(path.lastIndexOf("."));
                                    film.setSuffix(suffix);
                                    film.setFileName(film.getTitle() + suffix);
                                    File file = new File(SDPath.DOWNLOAD, film.getFileName());
                                    if (file.exists()) {
                                        film.setDownload("true");
                                    } else {
                                        film.setDownload("false");
                                    }

                                    film.setCount(obji.optString("size"));
                                    film.setAuth(obji.optString("auth"));
                                    film.setCanShow(obji.optString("canShow"));
                                    if (!obji.isNull("resRepCls")) {
                                        JSONObject resRepCls = obji.getJSONObject("resRepCls");
                                        film.setType(resRepCls.optString("name"));
                                        film.setThumb(HttpUrlUtil.SERVER_IMAGE + resRepCls.optString("icon"));
                                    }
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

    /**
     * 获取课程分类详情
     *
     * @param handler 接收结果
     * @param success 成功标识
     * @param error   错误标识
     */
    public void getResGroupCourseList(String user_id, String group_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RESGROUPCOURSELIST);
        params.addBodyParameter("group_id", group_id);
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
                            ArrayList<Film> list = new ArrayList<Film>();
                            if (!info.isNull("group")) {
                                JSONObject course = info.getJSONObject("group");

                                Film film = new Film();
                                film.setId(course.optString("id"));
                                film.setTitle(course.optString("title"));
                                film.setDescr(course.optString("descr"));
                                film.setTimeShow(course.optString("totalTimeShow"));
                                film.setCount(course.optString("cnt"));
                                film.setThumb(HttpUrlUtil.SERVER_IMAGE + course.optString("titlePic"));

                                list.add(0, film);
                            }
                            if (!info.isNull("courseList")) {
                                JSONArray others = info.getJSONArray("courseList");
                                for (int i = 0; i < others.length(); i++) {
                                    JSONObject obji = others.getJSONObject(i);
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
                            data.putParcelableArrayList("list", list);
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
     * 获取课程详情
     *
     * @param course_id 课程id
     * @param handler   接收结果
     * @param success   成功标识
     * @param error     错误标识
     */
    public void getResCourseDetail(String user_id, String course_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RESCOURSEDETAIL);
        params.addBodyParameter("course_id", course_id);
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
                            ArrayList<Film> list = new ArrayList<Film>();
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
                                film.setHasFavourite(course.optString("hasFavourite"));
                                list.add(0, film);
                            }
                            if (!info.isNull("others")) {
                                JSONArray others = info.getJSONArray("others");
                                for (int i = 0; i < others.length(); i++) {
                                    JSONObject obji = others.getJSONObject(i);
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
                            data.putParcelableArrayList("list", list);
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
    public void getResGroupList(String user_id, final Handler handler, final int success, final int error) {
        RequestParams params = new RequestParams(HttpUrlUtil.RESGROUPLIST);
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
                            JSONArray list = result.getJSONArray("info");
                            ArrayList<Film> filmList = new ArrayList<Film>();

                            for (int i = 0; i < list.length(); i++) {
                                JSONObject obji = list.getJSONObject(i);
                                Film film = new Film();
                                film.setId(obji.optString("id"));
                                film.setTitle(obji.optString("title"));
                                film.setDescr(obji.optString("descr"));
                                film.setTimeShow(obji.optString("totalTimeShow"));
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
                                    film.setTimeShow(obji.optString("timeShow"));
                                    film.setSort(obji.optString("sort"));
                                    film.setThumb(HttpUrlUtil.SERVER_IMAGE + obji.optString("thumb"));
                                    film.setSfrom(obji.optString("sfrom"));
                                    film.setDuration(obji.optString("duration"));
                                    film.setAuth(obji.optString("auth"));
                                    film.setHasFavourite(obji.optString("hasFavourite"));
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
