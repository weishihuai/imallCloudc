package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  不良反应结果 Enum
 */
public enum BadReactionResultCodeEnum implements ICodeEnum {
    /**
     * 痊愈
     */
    RECOVERY("RECOVERY", "痊愈"),
    /**
     * 好转
     */
    GETTING_BETTER("GETTING_BETTER", "好转"),
    /**
     * 未好转
     */
    NOT_GETTING_BETTER("NOT_GETTING_BETTER", "未好转"),
    /**
     * 不详
     */
    UNKNOWN("UNKNOWN", "不详"),
    /**
     * 有后遗症
     */
    REMNANT_SYMPTOM("REMNANT_SYMPTOM", "有后遗症"),
    /**
     * 死亡
     */
    DEATH("DEATH", "死亡");

    private String code;
    private String name;

    BadReactionResultCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionResultCodeEnum fromCode(String code) {
        for (BadReactionResultCodeEnum badReactionResultCodeEnum : BadReactionResultCodeEnum.values()) {
            if (badReactionResultCodeEnum.code.equals(code)) {
                return badReactionResultCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionResultCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
