package com.imall.iportal.dicts;

import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;


/**
 * Created by yys on 2017/2/16.
 */
public enum SmsInterfaceTypeCodeEnum implements ICodeEnum {
    SMS_INTERFACE_TYPE("SMS_INTERFACE_TYPE","短信接口类型"),

    EMAY("EMAY","亿美"),
    MONTNETS("MONTNETS","梦网");

    private String code;
    private String name;

    SmsInterfaceTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SmsInterfaceTypeCodeEnum fromCode(String code) {
        for (SmsInterfaceTypeCodeEnum codeEnum : SmsInterfaceTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(SmsInterfaceTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);

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
