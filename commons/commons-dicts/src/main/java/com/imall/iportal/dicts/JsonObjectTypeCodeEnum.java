package com.imall.iportal.dicts;

import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;


/**
 * Created by Donie on 2015/9/24.
 */
public enum JsonObjectTypeCodeEnum implements ICodeEnum {
    SMS_ACCOUNT("SMS_ACCOUNT", "短信账号"),
    EMAIL_ACCOUNT("EMAIL_ACCOUNT", "邮箱账号");


    private String code;
    private String name;

    JsonObjectTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static JsonObjectTypeCodeEnum fromCode(String code) {
        for (JsonObjectTypeCodeEnum codeEnum : JsonObjectTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(JsonObjectTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
