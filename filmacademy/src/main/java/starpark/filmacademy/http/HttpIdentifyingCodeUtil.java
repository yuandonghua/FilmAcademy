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
    //补充公司股东信息成功
    public static final int SUPPLEMENTARY_SHAREHOLDER_S = 28;
    //补充公司股东信息失败
    public static final int SUPPLEMENTARY_SHAREHOLDER_F = 27;
    //补充公司法人信息成功
    public static final int SUPPLEMENTARY_JURIDICAL_PERSON_S = 26;
    //补充公司法人信息失败
    public static final int SUPPLEMENTARY_JURIDICAL_PERSON_F = 25;
    //获取管家消息列表成功
    public static final int GET_MESSAGE_LIST_S = 24;
    //获取管家消息列表失败
    public static final int GET_MESSAGE_LIST_F = 23;
    //获取公司信息成功
    public static final int GET_COMPANY_INFO_S = 22;
    //获取公司信息失败
    public static final int GET_COMPANY_INFO_F = 21;
    //公司设立失败
    public static final int CREATE_COMPANY_F = 20;
    //公司设立成功
    public static final int CREATE_COMPANY_S = 19;
    //打开相机,拍摄反面
    public static final int OPEN_CAMERA_ID_BACK = 18;
    //打开相机,拍摄正面
    public static final int OPEN_CAMERA_ID_FRONT = 17;
    //删除用户地址-失败
    public static final int DELETE_ADDRESS_F = 16;
    // 删除用户地址-成功
    public static final int DELETE_ADDRESS_S = 15;
    //获取用户地址列表-失败
    public static final int GET_ADDRESS_LIST_F = 14;
    // 获取用户地址列表-成功
    public static final int GET_ADDRESS_LIST_S = 13;
    //新增地址-失败
    public static final int ADD_ADDRESS_F = 12;
    //新增地址-成功
    public static final int ADD_ADDRESS_S = 11;
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
