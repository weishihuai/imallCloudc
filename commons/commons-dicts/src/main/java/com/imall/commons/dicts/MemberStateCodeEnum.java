package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/17.
 * 会员状态Enum
 */
public enum MemberStateCodeEnum implements ICodeEnum {
    /**
     * 正常
     */
    NORMAL("NORMAL", "正常"),
    /**
     * 禁用
     */
    DISABLED("DISABLED", "禁用");

    private String code;
    private String name;

    MemberStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MemberStateCodeEnum fromCode(String code) {
        for (MemberStateCodeEnum memberStateCodeEnum : MemberStateCodeEnum.values()) {
            if (memberStateCodeEnum.code.equals(code)) {
                return memberStateCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(MemberStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toCode() {
        return code;
    }

    @Override
    public String toName() {
        return name;
    }
}
