package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  是否首次报告类型 Enum
 */
public enum BadReactionIsFirstRepTypeCodeEnum implements ICodeEnum {
    /**
     * 首次报告
     */
    FIRST_REG("FIRST_REG", "首次报告"),
    /**
     * 跟踪报告
     */
    FOLLOW_REG("FOLLOW_REG", "跟踪报告");

    private String code;
    private String name;

    BadReactionIsFirstRepTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionIsFirstRepTypeCodeEnum fromCode(String code) {
        for (BadReactionIsFirstRepTypeCodeEnum typeCodeEnum : BadReactionIsFirstRepTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionIsFirstRepTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
