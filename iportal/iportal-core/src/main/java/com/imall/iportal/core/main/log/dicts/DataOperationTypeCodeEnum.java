package com.imall.iportal.core.main.log.dicts;

import com.imall.commons.dicts.GlobalExt;
import com.imall.commons.dicts.ICodeEnum;

/**
 * 日志系统数据操作类型
 * User: ygw
 * Date: 17-8-29
 */
public enum DataOperationTypeCodeEnum implements ICodeEnum {
    /**
     * 保存
     */
    SAVE("SAVE", "保存"),

    /**
     * 更新
     */
    UPDATE("UPDATE", "更新"),

    /**
     * 删除
     */
    DELETE("DELETE", "删除");



    private String code;

    private String name;


    private DataOperationTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DataOperationTypeCodeEnum fromCode(String code) {
        for (DataOperationTypeCodeEnum codeEnum : DataOperationTypeCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(DataOperationTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    public String toCode() {
        return code;
    }

    public String toName() {
        return name;
    }
}
