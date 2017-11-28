package com.imall.iportal.dicts;


import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;

/**
 * 用户性别代码
 *
 */
public enum UserSexCodeEnum implements ICodeEnum {

    USER_SEX("USER_SEX", "用户类型"),

    MALE("MALE", "男"),

    FEMALE("FEMALE", "女"),

    SECRET("SECRET", "保密");

    private String code;
    private String name;

    private UserSexCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserSexCodeEnum fromCode(String code) {
        for (UserSexCodeEnum codeEnum : UserSexCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(UserSexCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
