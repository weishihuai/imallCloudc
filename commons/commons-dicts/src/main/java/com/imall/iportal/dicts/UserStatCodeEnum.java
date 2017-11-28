package com.imall.iportal.dicts;


import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;

/**
 * 管理员用户代码
 *
 */
public enum UserStatCodeEnum implements ICodeEnum {

    USER_STAT("USER_STAT", "用户状态"),

    NORMAL("NORMAL", "正常"),

    FREEZE("FREEZE", "冻结");

    private String code;
    private String name;

    private UserStatCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserStatCodeEnum fromCode(String code) {
        for (UserStatCodeEnum codeEnum : UserStatCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(UserStatCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
