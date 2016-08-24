package starpark.filmacademy.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import starpark.filmacademy.R;
import starpark.filmacademy.activity.FilmDetailedInfoActivity;
import starpark.filmacademy.adapter.FilmListAdapter;
import starpark.filmacademy.adapter.FilmResourceListAdapter;
import starpark.filmacademy.data.Film;
import starpark.filmacademy.http.FilmDataHttp;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.listener.OnItemClickListener;
import starpark.filmacademy.utils.ManageUserDataUtil;

/**
 * @description:下载资源界面
 * @author:袁东华 created at 2016/8/24 0024 下午 2:35
 */
public class DownloadPage {
    private static DownloadPage instance;
    private View view = null;
    private Activity activity = null;
    private RecyclerView recyclerView;
    private FilmResourceListAdapter filmResourceListAdapter;
    private ArrayList<Film> list;


    public void start(Activity activity, View view) {
        if (view != null && activity != null && (this.view == null && this.activity == null)) {
            this.activity = activity;
            this.view = view;
            initView();
            initData();
        }
    }

    public View getView() {
        return view;
    }


    public void initData() {
        if (ManageUserDataUtil.getInstance().getUserId(activity) != "") {
            //获取资源列表
            FilmDataHttp.getInstance().getResRepositist(
                    ManageUserDataUtil.getInstance().getUserId(activity), handler,
                    HttpIdentifyingCodeUtil.RESREPOSITIST_S,
                    HttpIdentifyingCodeUtil.RESREPOSITIST_E);
        } else {
            //没有用户id

        }

    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
//                activity)
//                .color(activity.getResources().getColor(R.color.black_14))
//                .size(activity.getResources().getDimensionPixelSize(
//                        R.dimen.divider_2dp))
//                .build());
        filmResourceListAdapter = new FilmResourceListAdapter(activity);
        recyclerView.setAdapter(filmResourceListAdapter);
        //点击条目
        filmResourceListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });

    }

    public Handler handler = new Handler() {
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //获取电影资源列表成功
                case HttpIdentifyingCodeUtil.RESREPOSITIST_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        list = data.getParcelableArrayList("list");
                        filmResourceListAdapter.setList(list);
                    }

                    break;
                //获取电影资源列表失败
                case HttpIdentifyingCodeUtil.RESREPOSITIST_E:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private DownloadPage() {


    }

    public static DownloadPage getInstance() {
        if (instance == null) {
            synchronized (FilmListPage.class) {
                if (instance == null) {
                    instance = new DownloadPage();
                }
            }
        }

        return instance;
    }
}
