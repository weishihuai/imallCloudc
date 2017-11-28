package com.imall.iportal.dicts;

import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;


/**
 * Created by lzx on 2017/2/16.
 */
public enum SendStatCodeEnum implements ICodeEnum {
    SEND_STAT("SEND_STAT","发送状态"),

    UN_SEND("UN_SEND","未发送"),
    FAIL("FAIL","发送失败"),
    SUCCESS("SUCCESS","发送成功");

    private String code;
    private String name;

    SendStatCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SendStatCodeEnum fromCode(String code) {
        for (SendStatCodeEnum codeEnum : SendStatCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(SendStatCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
