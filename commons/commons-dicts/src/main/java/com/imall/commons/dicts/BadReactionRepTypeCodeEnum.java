package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  不良反应报告类型 Enum
 */
public enum BadReactionRepTypeCodeEnum implements ICodeEnum {
    /**
     * 新的
     */
    NOVEL("NOVEL", "新的"),
    /**
     * 严重
     */
    SERIOUS("SERIOUS", "严重"),
    /**
     * 一般
     */
    COMMON("COMMON", "一般");

    private String code;
    private String name;

    BadReactionRepTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionRepTypeCodeEnum fromCode(String code) {
        for (BadReactionRepTypeCodeEnum typeCodeEnum : BadReactionRepTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionRepTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
