package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/6.
 * 会员卡使用状态Enum
 */
public enum MemberCardUseStatCodeEnum implements ICodeEnum {
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

    MemberCardUseStatCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MemberCardUseStatCodeEnum fromCode(String code) {
        for (MemberCardUseStatCodeEnum memberCardUseStatCodeEnum : MemberCardUseStatCodeEnum.values()) {
            if (memberCardUseStatCodeEnum.code.equals(code)) {
                return memberCardUseStatCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(MemberCardUseStatCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
