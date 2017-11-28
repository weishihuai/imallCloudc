package com.imall.commons.dicts;

/**
 * 操作状态代码
 * Created by CX on 2017/8/18
 */
public enum OperationStateCodeEnum implements ICodeEnum {
    /**
     * 启用
     */
    ENABLE("ENABLE", "启用"),
    /**
     * 停用
     */
    DISABLE("DISABLE", "停用");

    private String code;
    private String name;

    OperationStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static OperationStateCodeEnum fromCode(String code) {
        for (OperationStateCodeEnum goodsBatchStateCodeEnum : OperationStateCodeEnum.values()) {
            if (goodsBatchStateCodeEnum.code.equals(code)) {
                return goodsBatchStateCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(OperationStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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