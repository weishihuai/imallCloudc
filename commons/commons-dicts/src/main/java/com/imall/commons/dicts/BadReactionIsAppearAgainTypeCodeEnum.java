package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  是否 再次 出现 类型 Enum
 */
public enum BadReactionIsAppearAgainTypeCodeEnum implements ICodeEnum {
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
     * 未再使用
     */
    NO_LONGER_USED("NO_LONGER_USED", "未再使用");

    private String code;
    private String name;

    BadReactionIsAppearAgainTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionIsAppearAgainTypeCodeEnum fromCode(String code) {
        for (BadReactionIsAppearAgainTypeCodeEnum typeCodeEnum : BadReactionIsAppearAgainTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionIsAppearAgainTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
