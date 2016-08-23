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
    /**
     * 登陆
     */
    public static final String LOGIN = SERVER + "/userLoginWt.json";
    /**
     * 注册
     */
    public static final String REGIST = SERVER + "/userRegWt.json";
    /**
     * 修改密码
     */
    public static final String MIDIFY_PASSWORD = SERVER + "/pwdResetWt.json";
    /**
     * 修改手机号
     */
    public static final String MIDIFY_PHONE = SERVER + "/contactWayUpdWt.json";
    //课程详情
    public static final String RESCOURSEDETAIL = SERVER + "/resCourseDetail.json";
    //课程组分类
    public static final String RESGROUPLIST = SERVER + "/resGroupList.json";
    //课程列表
    public static final String RESCOURSELIST = SERVER + "/resCourseList.json";
}
