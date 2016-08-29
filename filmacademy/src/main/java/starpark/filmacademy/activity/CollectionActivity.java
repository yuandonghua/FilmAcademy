package starpark.filmacademy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import starpark.filmacademy.R;
import starpark.filmacademy.adapter.CollectionAdapter;
import starpark.filmacademy.adapter.FilmListAdapter;
import starpark.filmacademy.data.Film;
import starpark.filmacademy.http.FilmDataHttp;
import starpark.filmacademy.http.HttpIdentifyingCodeUtil;
import starpark.filmacademy.listener.OnItemClickListener;
import starpark.filmacademy.utils.ManageUserDataUtil;
import starpark.filmacademy.view.recyclerview.HorizontalDividerItemDecoration;

/**
 * @description:收藏夹界面
 * @author:袁东华 created at 2016/8/25 0025 下午 3:07
 */
@ContentView(R.layout.activity_collection)
public class CollectionActivity extends BaseActivity {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private CollectionAdapter collectionAdapter;
    private ArrayList<Film> list;

    @Override
    public void initTopView(String title) {
        super.initTopView("收藏夹");
    }

    @Override
    public void initData() {
        FilmDataHttp.getInstance().getSacUserFavouriteList(ManageUserDataUtil.getInstance().getUserId(activity),
                handler,
                HttpIdentifyingCodeUtil.SACUSERFAVOURITELIST_S,
                HttpIdentifyingCodeUtil.SACUSERFAVOURITELIST_E);

    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
                activity)
                .color(activity.getResources().getColor(R.color.black_14))
                .size(activity.getResources().getDimensionPixelSize(
                        R.dimen.divider_2dp))
                .build());
        collectionAdapter = new CollectionAdapter(activity);
        recyclerView.setAdapter(collectionAdapter);
        //点击条目
        collectionAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(activity, FilmDetailedInfoActivity.class);
                intent.putExtra("course_id",list.get(position).getId());
                activity.startActivity(intent);

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
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
                case HttpIdentifyingCodeUtil.SACUSERFAVOURITELIST_S:
                    Bundle data = msg.getData();
                    if (data != null) {
                        list = data.getParcelableArrayList("list");
                        collectionAdapter.setList(list);
                    }

                    break;
                //获取电影列表失败
                case HttpIdentifyingCodeUtil.SACUSERFAVOURITELIST_E:
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
