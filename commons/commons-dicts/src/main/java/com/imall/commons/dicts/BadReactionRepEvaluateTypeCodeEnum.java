package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  报告评价类型 Enum
 */
public enum BadReactionRepEvaluateTypeCodeEnum implements ICodeEnum {
    /**
     * 肯定
     */
    CERTAIN("CERTAIN", "肯定"),
    /**
     * 很可能
     */
    IN_ALL_PROBABILITY ("IN_ALL_PROBABILITY", "很可能"),
    /**
     * 可能
     */
    PROBABLY("PROBABLY", "可能"),
    /**
     * 可能无关
     */
    PROBABLY_IRRELEVANT("PROBABLY_IRRELEVANT", "可能无关"),
    /**
     * 待评价
     */
    WAIT_APPRAISE("WAIT_APPRAISE", "待评价"),
    /**
     * 无法评价
     */
    CANNOT_APPRAISE("CANNOT_APPRAISE", "无法评价");

    private String code;
    private String name;

    BadReactionRepEvaluateTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionRepEvaluateTypeCodeEnum fromCode(String code) {
        for (BadReactionRepEvaluateTypeCodeEnum typeCodeEnum : BadReactionRepEvaluateTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionRepEvaluateTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
