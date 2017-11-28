package com.imall.commons.dicts;

/**
 * Created by Administrator on 2015/9/24.
 * 资源类型
 */
public enum ResourceTypeCodeEnum implements ICodeEnum  {
    RESOURCE_TYPE("RESOURCE_TYPE", "资源类型"),
    ELM("ELM", "元素"),
    BTN("BTN", "按钮"),
    LNK("LNK", "链接"),
    MDL("MDL", "模块"),
    APP("APP", "应用");

    private String code;
    private String name;

    ResourceTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ResourceTypeCodeEnum fromCode(String code) {
        for (ResourceTypeCodeEnum codeEnum : ResourceTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(ResourceTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
