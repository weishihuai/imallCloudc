package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  报告人职业类型 Enum
 */
public enum BadReactionRepManProfessionTypeCodeEnum implements ICodeEnum {
    /**
     * 医生
     */
    DOCTOR("DOCTOR", "医生"),
    /**
     * 药师
     */
    APOTHECARY("APOTHECARY", "药师"),
    /**
     * 护士
     */
    NURSE("NURSE", "护士"),
    /**
     * 其他
     */
    OTHER("OTHER", "其他");

    private String code;
    private String name;

    BadReactionRepManProfessionTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionRepManProfessionTypeCodeEnum fromCode(String code) {
        for (BadReactionRepManProfessionTypeCodeEnum typeCodeEnum : BadReactionRepManProfessionTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionRepManProfessionTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
