package com.imall.iportal.dicts;

import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;


/**
 * 验证码类型
 */
public enum ValidateCodeTypeEnum implements ICodeEnum {
    NORMAL("NORMAL", "普通验证码"),
    PWD_VALIDATE_CODE("PWD_VALIDATE_CODE", "密码相关验证码"),
    OTHER("OTHER", "其他验证码");

    private String code;
    private String name;

    ValidateCodeTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ValidateCodeTypeEnum fromCode(String code) {
        for (ValidateCodeTypeEnum codeEnum : values()) {
            if (codeEnum.toCode().equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(ValidateCodeTypeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
