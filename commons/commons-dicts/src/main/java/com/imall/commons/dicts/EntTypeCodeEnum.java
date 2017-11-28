package com.imall.commons.dicts;

/**
 * Created by wsh on 2017/7/5.
 * 商品货位类型Enum
 */
public enum EntTypeCodeEnum implements ICodeEnum {

    PROPRIETARY_SHOP("PROPRIETARY_SHOP", "自营店"),
    FRANCHISE_STORE("FRANCHISE_STORE", "加盟店"),
    UNION_STORE("UNION_STORE", "联盟店");

    private String code;
    private String name;

    EntTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EntTypeCodeEnum fromCode(String code) {
        for (EntTypeCodeEnum storageSpaceTypeCodeEnum : EntTypeCodeEnum.values()) {
            if (storageSpaceTypeCodeEnum.code.equals(code)) {
                return storageSpaceTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(EntTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
