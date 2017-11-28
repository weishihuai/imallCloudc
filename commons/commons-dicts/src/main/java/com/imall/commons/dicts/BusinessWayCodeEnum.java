package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/5.
 * 商品货位类型Enum
 */
public enum BusinessWayCodeEnum implements ICodeEnum {

    PRODUCTION("PRODUCTION", "生产"),
    WHOLESALE("WHOLESALE", "批发"),
    retail("retail", "零售");

    private String code;
    private String name;

    BusinessWayCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BusinessWayCodeEnum fromCode(String code) {
        for (BusinessWayCodeEnum storageSpaceTypeCodeEnum : BusinessWayCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(BusinessWayCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
