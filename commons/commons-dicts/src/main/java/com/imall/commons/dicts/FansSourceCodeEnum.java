package com.imall.commons.dicts;

/**
 * Created by lxh on 2017/8/9.
 * 粉丝来源
 */
public enum FansSourceCodeEnum implements ICodeEnum {
    SHOP("SHOP", "门店"),

    OTHER("OTHER", "其他");

    private String code;
    private String name;

    private FansSourceCodeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static FansSourceCodeEnum fromCode(String code) {
        for (FansSourceCodeEnum codeEnum : values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(EventTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toName() {
        return name;
    }

    @Override
    public String toCode() {
        return code;
    }
}
