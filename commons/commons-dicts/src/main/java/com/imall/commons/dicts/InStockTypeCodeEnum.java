package com.imall.commons.dicts;

public enum InStockTypeCodeEnum implements ICodeEnum {
    RECEIVE("RECEIVE", "获赠"),
    OTHER("OTHER", "其他"),
    OVERFLOW("OVERFLOW", "报溢"),
    TAKE_BACK("TAKE_BACK", "领用退回");

    private String code;
    private String name;

    InStockTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static InStockTypeCodeEnum fromCode(String code) {
        for (InStockTypeCodeEnum codeEnum : InStockTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }

        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(InStockTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
