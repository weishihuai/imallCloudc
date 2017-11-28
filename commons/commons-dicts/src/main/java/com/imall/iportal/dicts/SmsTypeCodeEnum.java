package com.imall.iportal.dicts;

import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;


/**
 * 短信类型
 */
public enum SmsTypeCodeEnum implements ICodeEnum {
    VALIDATE_CODE("VALIDATE_CODE", "验证码");

    private String code;
    private String name;

    SmsTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SmsTypeCodeEnum fromCode(String code) {
        for (SmsTypeCodeEnum codeEnum : values()) {
            if (codeEnum.toCode().equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(SmsTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
