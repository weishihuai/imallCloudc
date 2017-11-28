package com.imall.commons.dicts;


public enum IndexManageStateCodeEnum implements ICodeEnum {
    INDEX_MANAGE_STATE("INDEX_MANAGE_STATE", "索引管理状态"),
    FREEING("FREEING","空闲中"),
    PROCESSING("PROCESSING","处理中");

    private String code;
    private String name;

    IndexManageStateCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static IndexManageStateCodeEnum fromCode(String code) {
        for (IndexManageStateCodeEnum codeEnum : IndexManageStateCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(IndexManageStateCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
