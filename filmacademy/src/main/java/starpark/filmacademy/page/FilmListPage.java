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

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

import starpark.filmacademy.R;
import starpark.filmacademy.activity.FilmDetailedInfoActivity;
import starpark.filmacademy.adapter.FilmListAdapter;
import starpark.filmacademy.data.Film;
import starpark.filmacademy.http.FilmDataHttp;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.listener.OnItemClickListener;
import starpark.filmacademy.utils.ManageUserDataUtil;

/**
 * @description:电影列表界面
 * @author:袁东华 created at 2016/8/22 0022 下午 2:40
 */
public class FilmListPage {
    private static FilmListPage instance;
    private View view = null;
    private Activity activity = null;
    private RecyclerView recyclerView;
    private FilmListAdapter filmListAdapter;
    private ArrayList<Film> list;

    private FilmListPage() {


    }

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
            //获取电影列表
            FilmDataHttp.getInstance().getResCourseList(ManageUserDataUtil.getInstance().getUserId(activity),
                    handler, HttpIdentifyingCodeUtil.GETRESCOURSELIST_S, HttpIdentifyingCodeUtil.GETRESCOURSELIST_E);
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
        filmListAdapter = new FilmListAdapter(activity);
        recyclerView.setAdapter(filmListAdapter);
        //点击条目
        filmListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(activity, FilmDetailedInfoActivity.class);
                intent.putExtra("course_id",list.get(position).getId());
                activity.startActivity(intent);

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
                        filmListAdapter.setList(list);
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

    public static FilmListPage getInstance() {
        if (instance == null) {
            synchronized (FilmListPage.class) {
                if (instance == null) {
                    instance = new FilmListPage();
                }
            }
        }

        return instance;
    }

}
