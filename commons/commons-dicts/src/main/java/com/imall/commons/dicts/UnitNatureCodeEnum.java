package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/5.
 *  单位 性质Enum
 */
public enum UnitNatureCodeEnum implements ICodeEnum {

    WHOLESALE_ENTERPRISE("WHOLESALE_ENTERPRISE", "批发企业"),
    MANUFACTURER("MANUFACTURER", "生产厂商");


    private String code;
    private String name;

    UnitNatureCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UnitNatureCodeEnum fromCode(String code) {
        for (UnitNatureCodeEnum storageSpaceTypeCodeEnum : UnitNatureCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(UnitNatureCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
    }

    @Override
    public String toCode() {
        return code;
    }

    @Override
    public String toName() {
        return name;
    }
}
