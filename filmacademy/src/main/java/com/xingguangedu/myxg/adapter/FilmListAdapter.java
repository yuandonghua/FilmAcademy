package com.xingguangedu.myxg.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.xutils.x;

import java.util.ArrayList;

import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.data.Film;
import com.xingguangedu.myxg.listener.OnItemClickListener;
import com.xingguangedu.myxg.utils.XUtils;

/**
 * @description:电影列表适配器
 * @author:袁东华 created at 2016/8/22 0022 下午 2:44
 */
public class FilmListAdapter extends Adapter<FilmListAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private Activity activity;
    private ArrayList<Film> list;

    public FilmListAdapter(Activity activity) {
        this.activity = activity;

    }

    public void setList(ArrayList<Film> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmlist, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        x.image().bind(holder.imageView,list.get(position).getThumb(), XUtils.getInstance().getImageOptions(activity));
        holder.title_tv.setText(list.get(position).getTitle());
        holder.time_tv.setText(list.get(position).getTimeShow());

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView title_tv;
        private TextView time_tv;

        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            time_tv = (TextView) itemView.findViewById(R.id.time_tv);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getLayoutPosition());

            }
        }
    }

    /**
     * @Description:设置条目点击监听,供外部调用
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;

    }
}
