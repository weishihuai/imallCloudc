package com.imall.commons.dicts;

/**
 * Created by frt on 2017/7/14.
 */
public enum DrugCuringStateCodeEnum implements ICodeEnum {
    CURED("CURED", "已养护"),

    NOT_CURED("NOT_CURED", "未养护");

    private String code;
    private String name;

    DrugCuringStateCodeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static DrugCuringStateCodeEnum fromCode(String code) {
        for (DrugCuringStateCodeEnum codeEnum : DrugCuringStateCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }

        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DrugCuringStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
