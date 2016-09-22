package com.xingguangedu.myxg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.adapter.HistoryAdapter;
import com.xingguangedu.myxg.data.Film;
import com.xingguangedu.myxg.http.FilmDataHttp;
import com.xingguangedu.myxg.http.HttpIdentifyingCodeUtil;
import com.xingguangedu.myxg.listener.OnItemClickListener;
import com.xingguangedu.myxg.utils.ManageUserDataUtil;
import com.xingguangedu.myxg.view.itemtouch.ItemTouchHelperCallback;

/**
 * @description:播放历史记录界面
 * @author:袁东华 created at 2016/8/25 0025 下午 5:12
 */
@ContentView(R.layout.activity_collection)
public class HistoryActivity extends BaseActivity {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private ArrayList<Film> list;

    @Override
    public void initTopView(String title) {
        super.initTopView("播放记录");
    }

    @Override
    public void initData() {
        FilmDataHttp.getInstance().getResHistoryList(ManageUserDataUtil.getInstance().getUserId(activity),
                handler,
                HttpIdentifyingCodeUtil.SACUSERFAVOURITELIST_S,
                HttpIdentifyingCodeUtil.SACUSERFAVOURITELIST_E);

    }

    @Override
    public void initView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
//                activity)
//                .color(activity.getResources().getColor(R.color.black_14))
//                .size(activity.getResources().getDimensionPixelSize(
//                        R.dimen.divider_2dp))
//                .build());
        historyAdapter = new HistoryAdapter(activity,handler);
        recyclerView.setAdapter(historyAdapter);
        //点击条目
        historyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(activity, FilmDetailedInfoActivity.class);
                intent.putExtra("course_id", list.get(position).getId());
                activity.startActivity(intent);

            }
        });
        ItemTouchHelperCallback callback=new ItemTouchHelperCallback(historyAdapter);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
                        historyAdapter.setList(list);
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
