package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  相关重要信息 Enum
 */
public enum BadReactionReferImportantInfTypeCodeEnum implements ICodeEnum {
    /**
     * 吸烟史
     */
    SMOKE_HISTORY("SMOKE_HISTORY", "吸烟史"),
    /**
     * 饮酒史
     */
    DRINK_HISTORY("DRINK_HISTORY", "饮酒史"),
    /**
     * 妊娠期
     */
    GESTATION_PERIOD("GESTATION_PERIOD", "妊娠期"),
    /**
     * 肝病史
     */
    HEPATOPATHY_HISTORY("HEPATOPATHY_HISTORY", "肝病史"),
    /**
     * 肾病史
     */
    NEPHROPATHY_HISTORY("NEPHROPATHY_HISTORY", "肾病史"),
    /**
     * 过敏史
     */
    ALLERGIC_HISTORY("ALLERGIC_HISTORY", "过敏史"),
    /**
     * 其他
     */
    OTHER("OTHER", "其他");

    private String code;
    private String name;

    BadReactionReferImportantInfTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionReferImportantInfTypeCodeEnum fromCode(String code) {
        for (BadReactionReferImportantInfTypeCodeEnum badReactionResultCodeEnum : BadReactionReferImportantInfTypeCodeEnum.values()) {
            if (badReactionResultCodeEnum.code.equals(code)) {
                return badReactionResultCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionReferImportantInfTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
