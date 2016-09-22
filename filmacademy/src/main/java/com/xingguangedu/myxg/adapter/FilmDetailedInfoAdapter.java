package com.xingguangedu.myxg.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

import java.util.ArrayList;

import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.activity.FilmDetailedInfoActivity;
import com.xingguangedu.myxg.data.Film;
import com.xingguangedu.myxg.http.FilmDataHttp;
import com.xingguangedu.myxg.http.HttpIdentifyingCodeUtil;
import com.xingguangedu.myxg.listener.OnItemClickListener;
import com.xingguangedu.myxg.utils.ManageUserDataUtil;
import com.xingguangedu.myxg.utils.XUtils;

/**
 * @description:电影详情的适配器
 * @author:袁东华 created at 2016/8/23 0023 下午 4:05
 */
public class FilmDetailedInfoAdapter extends Adapter<FilmDetailedInfoAdapter.ViewHolder> {
    //头部item
    public static final int TYPE_HEADER = 0;
    //正常item
    public static final int TYPE_NORMAL = 1;
    private OnItemClickListener mOnItemClickListener;
    private Activity activity;
    private ArrayList<Film> list;
    private View headerView;
    private Handler handler;

    public FilmDetailedInfoAdapter(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    public void setList(ArrayList<Film> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setHeaderView(View v) {
        headerView = v;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        if (viewType == TYPE_HEADER) {
            //加载头部View
            viewHolder = new ViewHolder(headerView, mOnItemClickListener);
        } else if (viewType == TYPE_NORMAL) {
            //加载推荐视频View
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmdetailedinfo, parent, false);
            viewHolder = new ViewHolder(view, mOnItemClickListener);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            holder.title_tv.setText(list.get(position).getTitle());
            holder.time_tv.setText(list.get(position).getTimeShow());
            holder.desc_tv.setText(list.get(position).getDescr());
            if (list.get(position).getHasFavourite() == "true") {
                holder.collect_ib.setImageResource(R.drawable.ic_collect_selected);
            }else{
                holder.collect_ib.setImageResource(R.drawable.ic_collect_unselected);
            }
            //点击收藏
            holder.collect_ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handler.sendEmptyMessage(0);
                    if (list.get(position).getHasFavourite() == "true") {
                        //已收藏,要取消
                        FilmDataHttp.getInstance().getRecCourseFavoriteDelWt(
                                ManageUserDataUtil.getInstance().getUserId(activity),
                                list.get(position).getId(), handler,
                                HttpIdentifyingCodeUtil.RECCOURSEFAVORITEDELWT_S,
                                HttpIdentifyingCodeUtil.RECCOURSEFAVORITEDELWT_E);
                    } else {
                        //未收藏,要收藏
                        FilmDataHttp.getInstance().getRecCourseFavoriteAddWt(
                                ManageUserDataUtil.getInstance().getUserId(activity),
                                list.get(position).getId(), handler,
                                HttpIdentifyingCodeUtil.RECCOURSEFAVORITEADDWT_S,
                                HttpIdentifyingCodeUtil.RECCOURSEFAVORITEADDWT_E);
                    }


                }
            });
            //点击分享
            holder.share_ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        } else {
            if (position == 1) {
                holder.title_rl.setVisibility(View.VISIBLE);
            } else {
                holder.title_rl.setVisibility(View.GONE);
            }
            x.image().bind(holder.imageView, list.get(position).getThumb(), XUtils.getInstance().getImageOptions(activity));
            holder.title_tv.setText(list.get(position).getTitle());
            holder.time_tv.setText(list.get(position).getTimeShow());
            holder.desc_tv.setText(list.get(position).getDescr());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, FilmDetailedInfoActivity.class);
                    intent.putExtra("course_id", list.get(position).getId());
                    activity.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView title_tv;
        private TextView time_tv;
        private TextView desc_tv;
        private TextView count_tv;
        private ImageButton collect_ib, share_ib;
        private RelativeLayout title_rl;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            if (itemView == headerView) {
                title_tv = (TextView) itemView.findViewById(R.id.title_tv);
                time_tv = (TextView) itemView.findViewById(R.id.time_tv);
                desc_tv = (TextView) itemView.findViewById(R.id.desc_tv);

                collect_ib = (ImageButton) itemView.findViewById(R.id.collect_ib);
                share_ib = (ImageButton) itemView.findViewById(R.id.share_ib);
            } else {
                this.onItemClickListener = onItemClickListener;
                itemView.setOnClickListener(this);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                title_tv = (TextView) itemView.findViewById(R.id.title_tv);
                time_tv = (TextView) itemView.findViewById(R.id.time_tv);
                title_rl = (RelativeLayout) itemView.findViewById(R.id.title_rl);
                desc_tv = (TextView) itemView.findViewById(R.id.desc_tv);
            }

        }

        @Override
        public void onClick(View v) {
            LogUtil.e("点击----");
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
