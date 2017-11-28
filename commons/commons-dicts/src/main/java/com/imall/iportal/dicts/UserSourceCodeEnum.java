package com.imall.iportal.dicts;


import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;

/**
 * 是否值代码
 *
 */
public enum UserSourceCodeEnum implements ICodeEnum {

    USER_SOURCE("USER_SOURCE", "用户来源"),

    /**
     * 1、前台注册 2、电话注册
     */
    FRONEND("FRONEND", "前台注册"),
    TEL("TEL", "电话注册"),
    /**
     * 3、QQ用户 4、微博用户 5、淘宝用户
     */
    QQ("QQ", "QQ用户"),
    WEIBO("WEIBO", "微博用户"),
    TAOBAO("TAOBAO", "淘宝用户"),
    /**
     * 6、人人网用户 7、豆瓣网用户 8、开心网用户 9、360
     */
    RENREN("RENREN", "人人网用户"),
    DOUBAN("DOUBAN", "豆瓣网用户"),
    KAIXIN("KAIXIN", "开心网用户"),
    _360("360", "360");

    private String code;
    private String name;

    private UserSourceCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserSourceCodeEnum fromCode(String code) {
        for (UserSourceCodeEnum codeEnum : UserSourceCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(UserSourceCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toCode() {
        return code;
    }


    @Override
    public String toName(){
        return name;
    }

}
