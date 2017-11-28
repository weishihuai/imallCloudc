package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  反应是否减轻类型 Enum
 */
public enum BadReactionResponseIsEaseTypeCodeEnum implements ICodeEnum {
    /**
     * 是
     */
    Y("Y", "是"),
    /**
     * 否
     */
    N("N", "否"),
    /**
     * 不明
     */
    UNKNOWN("UNKNOWN", "不明"),
    /**
     * 未停药或未减量
     */
    WITHOUT_OR_WITHOUT_REDUCTION("WITHOUT_OR_WITHOUT_REDUCTION", "未停药或未减量");

    private String code;
    private String name;

    BadReactionResponseIsEaseTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionResponseIsEaseTypeCodeEnum fromCode(String code) {
        for (BadReactionResponseIsEaseTypeCodeEnum typeCodeEnum : BadReactionResponseIsEaseTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionResponseIsEaseTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
