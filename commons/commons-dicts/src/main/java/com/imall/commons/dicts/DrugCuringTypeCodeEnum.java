package com.imall.commons.dicts;

/**
 * Created by frt on 2017/7/14.
 */
public enum DrugCuringTypeCodeEnum implements ICodeEnum {
    FOCUS("FOCUS", "重点"),

    NORMAL("NORMAL", "常规");

    private String code;
    private String name;

    DrugCuringTypeCodeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static DrugCuringTypeCodeEnum fromCode(String code) {
        for (DrugCuringTypeCodeEnum codeEnum : DrugCuringTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }

        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DrugCuringTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toCode() {
        return code;
    }

    @Override
    public String toName(){
        return name;
    }
}
