package starpark.filmacademy.http;

/**
 * @description:url数据
 * @author:袁东华 created at 2016/7/20 0020 上午 11:09
 */
public class HttpUrlUtil {
    //域名
    public static final String SERVER = "http://edifc.dressbook.cn";
    //图片地址
    public static final String SERVER_IMAGE = "http://eddt.dressbook.cn";
    //登陆
    public static final String LOGIN = SERVER + "/userLoginWt.json";
    //注册
    public static final String REGIST = SERVER + "/userRegWt.json";
    //修改密码
    public static final String MIDIFY_PASSWORD = SERVER + "/pwdResetWt.json";
    //课程组详情
    public static final String RESGROUPCOURSELIST = SERVER + "/resGroupCourseList.json";
    //课程详情
    public static final String RESCOURSEDETAIL = SERVER + "/resCourseDetail.json";
    //课程组分类
    public static final String RESGROUPLIST = SERVER + "/resGroupList.json";
    //课程列表
    public static final String RESCOURSELIST = SERVER + "/resCourseList.json";
    //资源列表
    public static final String RESREPOSITIST = SERVER + "/resRepositist.json";
    //收藏列表
    public static final String SACUSERFAVOURITELIST = SERVER + "/sacUserFavouriteList.json";
    //添加收藏
    public static final String RECCOURSEFAVORITEADDWT = SERVER + "/recCourseFavoriteAddWt.json";
    //取消收藏
    public static final String RECCOURSEFAVORITEDELWT = SERVER + "/recCourseFavoriteDelWt.json";
    //添加播放历史
    public static final String RECCOURSEPLAYHISWT = SERVER + "/recCoursePlayHisWt.json";
    //播放历史记录
    public static final String RESHISTORYLIST = SERVER + "/resHistoryList.json";
    //修改个人资料
    public static final String SACUSERINFOUPDWT = SERVER + "/sacUserInfoUpdWt.json";
    //获取个人资料
    public static final String SACUSERINFOGET = SERVER + "/sacUserInfoGet.json";
    //用户反馈
    public static final String SACCOMMENTWT = SERVER + "/sacCommentWt.json";
    //忘记密码
    public static final String FORGET_PASSWORD = SERVER + "/pwdResetWt.json";
    //删除历史记录
    public static final String RESHISTORYDELWT = SERVER + "/resHistoryDelWt.json";
}
