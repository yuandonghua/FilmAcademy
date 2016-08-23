package starpark.filmacademy.page;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import starpark.filmacademy.R;
import starpark.filmacademy.adapter.FilmCategoryAdapter;
import starpark.filmacademy.data.Film;
import starpark.filmacademy.http.FilmDataHttp;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.listener.OnItemClickListener;
import starpark.filmacademy.utils.ManageUserDataUtil;

/**
 * @description:电影分类列表界面
 * @author:袁东华 created at 2016/8/23 0023 上午 11:10
 */
public class FilmCategoryPage {
    private static FilmCategoryPage instance;

    private View view = null;
    private Activity activity = null;
    private RecyclerView recyclerView;
    private FilmCategoryAdapter filmCategoryAdapter;
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

    private void initData() {
        if (ManageUserDataUtil.getInstance().getUserId(activity) != "") {
            //获取电影列表
            FilmDataHttp.getInstance().getResGroupList(
                    handler, HttpIdentifyingCodeUtil.GETRESCOURSELIST_S, HttpIdentifyingCodeUtil.GETRESCOURSELIST_E);
        } else {
            //没有用户id

        }

    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
//                activity)
//                .color(activity.getResources().getColor(R.color.black_14))
//                .size(activity.getResources().getDimensionPixelSize(
//                        R.dimen.divider_2dp))
//                .build());
        filmCategoryAdapter = new FilmCategoryAdapter(activity);
        recyclerView.setAdapter(filmCategoryAdapter);
        filmCategoryAdapter.setOnItemClickListener(new OnItemClickListener() {
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
                //获取电影列表成功
                case HttpIdentifyingCodeUtil.GETRESCOURSELIST_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        list = data.getParcelableArrayList("list");
                        filmCategoryAdapter.setList(list);
                    }

                    break;
                //获取电影列表失败
                case HttpIdentifyingCodeUtil.GETRESCOURSELIST_E:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private FilmCategoryPage() {


    }

    public static FilmCategoryPage getInstance() {
        if (instance == null) {
            synchronized (FilmCategoryPage.class) {
                if (instance == null) {
                    instance = new FilmCategoryPage();
                }
            }
        }

        return instance;
    }
}
