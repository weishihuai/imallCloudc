package com.imall.iportal.core.main.log.dicts;


import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;

/**
 * 日志类型
 * User: ygw
 * Date: 17-8-29
 */
public enum LogTypeCodeEnum implements ICodeEnum {

    //管理员登录或注销
    ADMIN("ADMIN", "管理员"),

    SYS_USER("SYS_USER", "员工"),

    //平台商品档案新增或修改、删除等操作
    GOODS_DOC("GOODS_DOC", "平台商品档案"),

    //门店商品新增或修改、删除等操作
    GOODS("GOODS", "门店商品"),

    ORDER("ORDER", "订单");



    private String code;

    private String name;


    private LogTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static LogTypeCodeEnum fromCode(String code) {
        for (LogTypeCodeEnum codeEnum : LogTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(LogTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    public String toCode() {
        return code;
    }

    public String toName() {
        return name;
    }
}
