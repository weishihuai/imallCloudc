package com.imall.commons.dicts;

/**
 * yang
 * 系统参数
 */
public enum ParamGroupTypeCodeEnum implements ICodeEnum {
    USER_DEFINED("USER_DEFINED", "用户自定义"),
    OTHER_PROMOTION("OTHER_PROMOTION", "其他参数"),
    FOR_STORAGE("FOR_STORAGE", "仅存储"); //如果参数需要重新组织到其它独立view，将存储的类型设置为仅存储，不出现参数定义里管理


    private String code;
    private String name;

    private ParamGroupTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ParamGroupTypeCodeEnum fromCode(String code) {
        for (ParamGroupTypeCodeEnum codeEnum : ParamGroupTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(ParamGroupTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
