package com.imall.iportal.core.main.log.dicts;


import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;

/**
 * User: ygw
 * Date: 17-8-29
 */
public enum  LogColumnTypeEnum implements ICodeEnum {
    /**
     * 正常
     */
    NORMAL("NORMAL", "正常"),

    /**
     * 主键
     */
    PRIMARY_KEY("PRIMARY_KEY", "主键"),

    /**
     * 外键
     */
    FOREIGN_KEY("FOREIGN_KEY", "外键");


    private String code;
    private String name;

    private LogColumnTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static LogColumnTypeEnum fromCode(String code) {
        for (LogColumnTypeEnum codeEnum : LogColumnTypeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(LogColumnTypeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
