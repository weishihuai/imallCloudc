package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 -  药品信息类型代码 Enum
 */
public enum BadReactionDrugInfTypeCodeEnum implements ICodeEnum {
    SUSPECT_DRUG("SUSPECT_DRUG", "怀疑药品"),
    BLEND_DRUG("BLEND_DRUG", "并用药品");

    private String code;
    private String name;

    BadReactionDrugInfTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BadReactionDrugInfTypeCodeEnum fromCode(String code) {
        for (BadReactionDrugInfTypeCodeEnum typeCodeEnum : BadReactionDrugInfTypeCodeEnum.values()) {
            if (typeCodeEnum.code.equals(code)) {
                return typeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BadReactionDrugInfTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
