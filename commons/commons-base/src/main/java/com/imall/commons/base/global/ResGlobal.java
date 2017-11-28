package com.imall.commons.base.global;

/**
 * 异常编码
 */
public class ResGlobal {

    public static final String RESOURCE_DELETE_ERROR = "RESOURCE_DELETE_ERROR";//删除资源错误
    public static final String RESOURCE_RELATE_DELETE_ERROR = "RESOURCE_RELATE_DELETE_ERROR";//资源被菜单关联，不允许删除
    public static final String SWITCH_JOB_ID_NULL_ERROR = "SWITCH_JOB_ID_NULL_ERROR";//切换岗位，岗位ID为空错误
    public static final String SWITCH_JOB_IDENTITY_AUTH_ERROR = "SWITCH_JOB_IDENTITY_AUTH_ERROR";//切换岗位，没有授予您身份xxx，不能切换
    public static final String OPERATION_NO_PERMISSION = "OPERATION_NO_PERMISSION";//当前操作，您没有权限！！！
    public static final String CHECK_IS_TRUST_APP_FAILED = "CHECK_IS_TRUST_APP_FAILED";//不是信任的app，验证失败！！！
    public static final String DICT_DELETE_RELATE_ERROR = "APP_DICT_ERROR";//删除字典错误
    public static final String FILE_CATEGORY_DELETE_ERROR = "FILE_CATEGORY_DELETE_ERROR";//存在子节点，删除失败
    public static final String APP_RELATE_DELETE_ERROR = "APP_RELATE_DELETE_ERROR";//应用被资源关联，不允许删除
    public static final String JOB_RELATE_DELETE_ERROR = "JOB_RELATE_DELETE_ERROR";//岗位被资源关联，不允许删除
    public static final String MENU_DELETE_ERROR = "MENU_DELETE_ERROR";//删除菜单错误
    public static final String ORG_RELATE_USER_DELETE_ERROR = "ORG_RELATE_USER_DELETE_ERROR";//删除组织错误
    public static final String ORG_RELATE_SERVICE_DELETE_ERROR = "ORG_RELATE_SERVICE_DELETE_ERROR";//删除组织错误
    public static final String USER_ORG_JOR_RELATE_DELETE_ERROR = "USER_ORG_JOR_RELATE_DELETE_ERROR";//删除用户错误
    public static final String WEB_UPLOAD_FILE_ERROR = "WEB_UPLOAD_FILE_ERROR";//上传文件错误
    public static final String ERRORS_IDSF_ALL = "ERRORS_IDSF_ALL";
    public static final String USER_NAME_IS_EXIST = "USER_NAME_IS_EXIST";//用户名称已被注册
    public static final String USER_MAIN_JOB_NOT_EXIST = "USER_MAIN_JOB_NOT_EXIST";//此用户没有设置主岗位
    public static final String DEFAULT_ERROR_OBJECT_NOT_EXIST = "DEFAULT_ERROR_OBJECT_NOT_EXIST";//对象不存在，公用错误
    public static final String COMMON_OBJECT_NO_FOUND = "COMMON_OBJECT_NO_FOUND";   //对象找不到公共异常
    public static final String PASSWORD_ERROR = "PASSWORD_ERROR";   //密码错误
    public static final String COMMON_ERROR = "COMMON_ERROR";   //通用错误
}
