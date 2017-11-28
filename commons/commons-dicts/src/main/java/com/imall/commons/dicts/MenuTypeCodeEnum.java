package com.imall.commons.dicts;

/**
 * Created by Donie on 2015/9/24.
 */
public enum MenuTypeCodeEnum implements ICodeEnum{
    MENU_TYPE("MENU_TYPE", "菜单类型"),
    APP("APP", "应用"),
    MDL("MDL", "模块"),
    MENU("MENU", "菜单"),
    BTN("BTN", "按钮");


    private String code;
    private String name;

    MenuTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MenuTypeCodeEnum fromCode(String code) {
        for (MenuTypeCodeEnum codeEnum : MenuTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(MenuTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
