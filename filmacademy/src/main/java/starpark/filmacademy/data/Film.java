package starpark.filmacademy.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @description:电影信息
 * @author:袁东华 created at 2016/8/22 0022 下午 5:48
 */
public class Film implements Parcelable {
    //电影id
    private String id = "";
    //电影标题
    private String title = "";
    //电影描述
    private String descr = "";
    //电影链接
    private String url = "";
    //电影上线时间
    private String addTimeShow = "";
    //电影时长
    private String timeShow = "";
    //电影分类
    private String sort = "";
    //电影图片
    private String thumb = "";
    private String sfrom = "";
    private String duration = "";
    private String auth = "";
    private String count = "";
    public Film() {
    }

    protected Film(Parcel in) {
        id = in.readString();
        title = in.readString();
        descr = in.readString();
        url = in.readString();
        addTimeShow = in.readString();
        sort = in.readString();
        thumb = in.readString();
        sfrom = in.readString();
        duration = in.readString();
        auth = in.readString();
        count = in.readString();
        timeShow = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(descr);
        dest.writeString(url);
        dest.writeString(addTimeShow);
        dest.writeString(sort);
        dest.writeString(thumb);
        dest.writeString(sfrom);
        dest.writeString(duration);
        dest.writeString(auth);
        dest.writeString(count);
        dest.writeString(timeShow);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddTimeShow() {
        return addTimeShow;
    }

    public void setAddTimeShow(String addTimeShow) {
        this.addTimeShow = addTimeShow;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSfrom() {
        return sfrom;
    }

    public void setSfrom(String sfrom) {
        this.sfrom = sfrom;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTimeShow() {
        return timeShow;
    }

    public void setTimeShow(String timeShow) {
        this.timeShow = timeShow;
    }
}
