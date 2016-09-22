package com.xingguangedu.myxg.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.x;

import java.util.ArrayList;

import com.xingguangedu.myxg.R;
import com.xingguangedu.myxg.data.Film;
import com.xingguangedu.myxg.http.FilmDataHttp;
import com.xingguangedu.myxg.http.HttpIdentifyingCodeUtil;
import com.xingguangedu.myxg.http.SDPath;
import com.xingguangedu.myxg.listener.OnItemClickListener;
import com.xingguangedu.myxg.utils.FileOpenWayUtil;
import com.xingguangedu.myxg.utils.XUtils;

/**
 * @description:电影资源下载列表适配器
 * @author:袁东华 created at 2016/8/24 0024 下午 3:10
 */
public class FilmResourceListAdapter extends Adapter<FilmResourceListAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private Activity activity;
    private ArrayList<Film> list;
    private Handler handler;
    private Drawable drawable_open, drawable_download;

    public FilmResourceListAdapter(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;
        drawable_open = activity.getResources().getDrawable(R.mipmap.ic_open_file_yellow);
        drawable_open.setBounds(0, 0, drawable_open.getMinimumWidth(), drawable_open.getMinimumHeight());
        drawable_download = activity.getResources().getDrawable(R.mipmap.ic_download_black);
        drawable_download.setBounds(0, 0, drawable_download.getMinimumWidth(), drawable_download.getMinimumHeight());
    }

    public void setList(ArrayList<Film> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmresourcelist, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        x.image().bind(holder.imageView, list.get(position).getThumb(),
                XUtils.getInstance().getImageOptions(activity,ImageView.ScaleType.CENTER_INSIDE));
        holder.title_tv.setText(list.get(position).getTitle());
        holder.desc_tv.setText(list.get(position).getDescr());
        holder.type_tv.setText(list.get(position).getType());
        if ("true".equals(list.get(position).getDownload())) {
            holder.download_tv.setText("打开");

            holder.download_tv.setCompoundDrawables(null, drawable_open, null, null);
        } else {
            holder.download_tv.setText(list.get(position).getCount() + "M");
            holder.download_tv.setCompoundDrawables(null, drawable_download, null, null);
        }


        holder.download_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("true".equals(list.get(position).getDownload())) {
                    //点击打开
                    FileOpenWayUtil.getInstance(activity).openFiles(SDPath.DOWNLOAD+"/"+list.get(position).getFileName());
                    FileOpenWayUtil.getInstance(activity).openFiles(SDPath.DOWNLOAD);
                } else {
                    //点击下载
                    FilmDataHttp.getInstance().downFilmResource(SDPath.DOWNLOAD+"/"+list.get(position).getFileName(),
                            list.get(position).getPath(),handler,
                            HttpIdentifyingCodeUtil.DOWNLOAD_FILE_S,HttpIdentifyingCodeUtil.DOWNLOAD_FILE_E);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView title_tv;
        private TextView download_tv;
        private TextView desc_tv,type_tv;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            download_tv = (TextView) itemView.findViewById(R.id.download_tv);
            desc_tv = (TextView) itemView.findViewById(R.id.desc_tv);
            type_tv = (TextView) itemView.findViewById(R.id.type_tv);
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
