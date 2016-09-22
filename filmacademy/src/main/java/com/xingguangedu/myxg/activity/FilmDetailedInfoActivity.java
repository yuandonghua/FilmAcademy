package com.xingguangedu.myxg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.adapter.FilmDetailedInfoAdapter;
import com.xingguangedu.myxg.data.Film;
import com.xingguangedu.myxg.http.FilmDataHttp;
import com.xingguangedu.myxg.http.HttpIdentifyingCodeUtil;
import com.xingguangedu.myxg.listener.OnItemClickListener;
import com.xingguangedu.myxg.utils.ManageUserDataUtil;
import com.xingguangedu.myxg.utils.XUtils;
import com.xingguangedu.myxg.view.recyclerview.HorizontalDividerItemDecoration;

/**
 * @description:电影详情界面
 * @author:袁东华 created at 2016/8/23 0023 下午 2:33
 */
@ContentView(R.layout.activity_filmdetailedinfo)
public class FilmDetailedInfoActivity extends BaseActivity {
    @ViewInject(R.id.header_iv)
    private ImageView header_iv;
    @ViewInject(R.id.toolbar_layout)
    private CollapsingToolbarLayout toolbar_layout;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.fab)
    private FloatingActionButton fab;
    private String course_id = "";
    private FilmDetailedInfoAdapter filmDetailedInfoAdapter;
    private ArrayList<Film> list = new ArrayList<>();

    @Override
    public void initTopView(String title) {
      super.initTopView(title);

        toolbar_layout.setTitle(title);
    }

    @Override
    public void receiveIntentData() {
        course_id = getIntent().getStringExtra("course_id");
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
                activity)
                .color(activity.getResources().getColor(R.color.white))
                .size(activity.getResources().getDimensionPixelSize(
                        R.dimen.divider_2dp))
                .build());
        filmDetailedInfoAdapter = new FilmDetailedInfoAdapter(activity,handler);
        View headerView = LayoutInflater.from(activity).inflate(R.layout.header_filmdetailedinfo, recyclerView, false);
        filmDetailedInfoAdapter.setHeaderView(headerView);
        recyclerView.setAdapter(filmDetailedInfoAdapter);
        //点击条目
        filmDetailedInfoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position!=0){
                    Intent intent=new Intent(activity,FilmDetailedInfoActivity.class);
                    intent.putExtra("course_id",list.get(position).getId());
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void initListener() {
        //点击播放按钮
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list != null && list.size() > 0) {
                    Intent intent = new Intent(activity, PlayFilmActivity.class);
                    intent.putExtra("url", list.get(0).getUrl());
                    intent.putExtra("title", list.get(0).getTitle());
                    intent.putExtra("course_id", list.get(0).getId());
                    startActivity(intent);

                }
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        //点击头部图片
        header_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PlayFilmActivity.class);
                intent.putExtra("url", list.get(0).getUrl());
                intent.putExtra("title", list.get(0).getTitle());
                intent.putExtra("course_id", list.get(0).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        //获取课程详情
        FilmDataHttp.getInstance().getResCourseDetail(
                ManageUserDataUtil.getInstance().getUserId(activity),
                course_id, handler,
                HttpIdentifyingCodeUtil.RESCOURSEDETAIL_S,
                HttpIdentifyingCodeUtil.RESCOURSEDETAIL_E);
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
                //获取电影成功
                case HttpIdentifyingCodeUtil.RESCOURSEDETAIL_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        list = data.getParcelableArrayList("list");
                        filmDetailedInfoAdapter.setList(list);
                        if (list != null && list.size() > 0) {

                            x.image().bind(header_iv, list.get(0).getThumb(), XUtils.getInstance().getImageOptions(activity));
                        }
                    }

                    break;
                //获取电影失败
                case HttpIdentifyingCodeUtil.RESCOURSEDETAIL_E:
                    Bundle errorData = msg.getData();
                    if (errorData != null) {
                        String message = errorData.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case HttpIdentifyingCodeUtil.RECCOURSEFAVORITEADDWT_S:
                    progressDialog.dismiss();
                    Bundle data2=msg.getData();
                    if (data2!=null){
                        String message = data2.getString("message");
                        Toast.makeText(activity, "收藏成功", Toast.LENGTH_SHORT).show();
                    }
                    initData();
                    break;
                case HttpIdentifyingCodeUtil.RECCOURSEFAVORITEADDWT_E:
                    progressDialog.dismiss();
                    Bundle data1=msg.getData();
                    if (data1!=null){
                        String message = data1.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    initData();
                    break;
                case HttpIdentifyingCodeUtil.RECCOURSEFAVORITEDELWT_S:
                    progressDialog.dismiss();
                    Bundle data3=msg.getData();
                    if (data3!=null){
                        String message = data3.getString("message");
                        Toast.makeText(activity, "取消收藏成功", Toast.LENGTH_SHORT).show();
                    }
                    initData();
                    break;
                case HttpIdentifyingCodeUtil.RECCOURSEFAVORITEDELWT_E:
                    progressDialog.dismiss();
                    Bundle data4=msg.getData();
                    if (data4!=null){
                        String message = data4.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                    initData();
                    break;
                case 0:
                    progressDialog.show();
                    break;
            }
        }
    };

}
