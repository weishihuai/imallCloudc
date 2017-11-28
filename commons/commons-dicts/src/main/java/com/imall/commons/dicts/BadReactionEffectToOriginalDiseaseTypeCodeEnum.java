package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  对原患疾病影响类型 Enum
 */
public enum BadReactionEffectToOriginalDiseaseTypeCodeEnum implements ICodeEnum {
    /**
     * 不明显
     */
    NON_CLEAR("NON_CLEAR", "不明显"),
    /**
     * 病程延长
     */
    PROLONGE_COURSE("PROLONGE_COURSE", "病程延长"),
    /**
     * 病情加重
     */
    ILLNESS_WORSE("ILLNESS_WORSE", "病情加重"),
    /**
     * 导致后遗症
     */
    CAUSE_SEQUEL("CAUSE_SEQUEL", "导致后遗症"),
    /**
     * 导致死亡
     */
    CAUSE_DEATH("CAUSE_DEATH", "导致死亡");

    private String code;
    private String name;

    BadReactionEffectToOriginalDiseaseTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionEffectToOriginalDiseaseTypeCodeEnum fromCode(String code) {
        for (BadReactionEffectToOriginalDiseaseTypeCodeEnum typeCodeEnum : BadReactionEffectToOriginalDiseaseTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionEffectToOriginalDiseaseTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
