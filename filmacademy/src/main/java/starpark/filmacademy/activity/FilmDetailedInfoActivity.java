package starpark.filmacademy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import starpark.filmacademy.R;
import starpark.filmacademy.adapter.FilmDetailedInfoAdapter;
import starpark.filmacademy.adapter.FilmListAdapter;
import starpark.filmacademy.data.Film;
import starpark.filmacademy.http.FilmDataHttp;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.listener.OnItemClickListener;
import starpark.filmacademy.utils.XUtils;
import starpark.filmacademy.view.recyclerview.HorizontalDividerItemDecoration;

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
    private Film film;
    private FilmDetailedInfoAdapter filmDetailedInfoAdapter;
    private ArrayList<Film> list = new ArrayList<>();

    @Override
    public void initTopView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar_layout.setTitle(" ");
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
        filmDetailedInfoAdapter = new FilmDetailedInfoAdapter(activity);
        View headerView = LayoutInflater.from(activity).inflate(R.layout.header_filmdetailedinfo, recyclerView, false);
        filmDetailedInfoAdapter.setHeaderView(headerView);
        recyclerView.setAdapter(filmDetailedInfoAdapter);
        //点击条目
        filmDetailedInfoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LogUtil.e("点击----");

            }
        });
    }

    @Override
    public void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list != null && list.size() > 0) {

                    Intent intent = new Intent(activity, PlayFilmActivity.class);
                    intent.putExtra("url", list.get(0).getUrl());
                    intent.putExtra("title", list.get(0).getTitle());
                    startActivity(intent);

                }
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void initData() {
        //获取课程详情
        FilmDataHttp.getInstance().getResCourseDetail(course_id, handler,
                HttpIdentifyingCodeUtil.RESCOURSEDETAIL_S, HttpIdentifyingCodeUtil.RESCOURSEDETAIL_E);
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
            }
        }
    };

}
