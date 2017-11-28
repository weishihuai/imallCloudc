package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  既往药品 不良反应 Enum
 */
public enum BadReactionDrugBadReactionTypeCodeEnum implements ICodeEnum {
    /**
     * 有
     */
    HAS("HAS", "有"),
    /**
     * 无
     */
    NO("NO", "无"),
    /**
     * 不详
     */
    UNSPECIFIED("UNSPECIFIED", "不详");

    private String code;
    private String name;

    BadReactionDrugBadReactionTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionDrugBadReactionTypeCodeEnum fromCode(String code) {
        for (BadReactionDrugBadReactionTypeCodeEnum typeCodeEnum : BadReactionDrugBadReactionTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionDrugBadReactionTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
