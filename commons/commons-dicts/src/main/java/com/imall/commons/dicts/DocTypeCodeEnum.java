package com.imall.commons.dicts;

/**
 * Created by frt on 2017/8/10.
 */
public enum DocTypeCodeEnum implements ICodeEnum{
    SYSTEMS_AND_PROCESSES("SYSTEMS_AND_PROCESSES", "制度与流程"),
    GSP_CHECK_TABLE("GSP_CHECK_TABLE", "GSP检查表");

    private String code;
    private String name;

    DocTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DocTypeCodeEnum fromCode(String code) {
        for (DocTypeCodeEnum codeEnum : DocTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DocTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
