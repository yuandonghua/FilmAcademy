package starpark.filmacademy.http;

/**
 * @description:网络请求的一些标识
 * @author:袁东华 created at 2016/7/20 0020 上午 11:08
 */
public class HttpIdentifyingCodeUtil {
    //获取课程列表成功
    public static final int GETRESCOURSELIST_E = 32;
    //获取课程列表失败
    public static final int GETRESCOURSELIST_S = 31;
    public static final int RESCOURSEDETAIL_E = 30;
    public static final int RESCOURSEDETAIL_S = 29;
    public static final int RESREPOSITIST_E = 28;
    public static final int RESREPOSITIST_S = 27;
    public static final int SACUSERFAVOURITELIST_E = 26;
    public static final int SACUSERFAVOURITELIST_S = 25;
    public static final int RECCOURSEFAVORITEADDWT_E = 24;
    public static final int RECCOURSEFAVORITEADDWT_S = 23;
    public static final int RECCOURSEPLAYHISWT_E = 22;
    public static final int RECCOURSEPLAYHISWT_S = 21;
    public static final int RECCOURSEFAVORITEDELWT_E = 20;
    public static final int RECCOURSEFAVORITEDELWT_S = 19;
    public static final int SACUSERINFOUPDWT_S = 18;
    public static final int SACUSERINFOUPDWT_E = 17;
    public static final int SACUSERINFOGET_S = 16;
    public static final int SACUSERINFOGET_E = 15;
    public static final int DOWNLOAD_FILE_S = 14;
    public static final int DOWNLOAD_FILE_E = 13;
    public static final int SACCOMMENTWT_S = 12;
    public static final int SACCOMMENTWT_E = 11;
    //修改邮箱-失败
    public static final int MODIFY_EMAIL_F = 10;
    //修改邮箱-成功
    public static final int MODIFY_EMAIL_S = 9;
    //修改手机号-失败
    public static final int MODIFY_PHONE_F = 8;
    //修改手机号-成功
    public static final int MODIFY_PHONE_S = 7;
    //修改密码-失败
    public static final int MODIFY_PASSWORD_F = 6;
    //修改密码-成功
    public static final int MODIFY_PASSWORD_S = 5;
    // 注册-失败
    public static final int REGIST_E = 4;
    //注册-成功
    public static final int REGIST_S = 3;
    //登陆-失败
    public static final int LOGIN_E = 2;
    //登陆-成功
    public static final int LOGIN_S = 1;

}
