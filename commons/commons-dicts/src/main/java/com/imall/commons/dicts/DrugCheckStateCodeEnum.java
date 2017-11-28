package com.imall.commons.dicts;

/**
 * Created by frt on 2017/7/14.
 */
public enum DrugCheckStateCodeEnum implements ICodeEnum {
    CHECKED("CHECKED", "已检查"),

    NOT_CHECKED("NOT_CHECKED", "未检查");

    private String code;
    private String name;

    DrugCheckStateCodeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static DrugCheckStateCodeEnum fromCode(String code) {
        for (DrugCheckStateCodeEnum codeEnum : DrugCheckStateCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }

        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DrugCheckStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
