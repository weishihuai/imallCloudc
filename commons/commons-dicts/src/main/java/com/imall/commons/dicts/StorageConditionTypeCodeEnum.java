package com.imall.commons.dicts;

/**
 * Created by caidapao on 2017/7/6.
 * 商品储存状态Enum
 */
public enum StorageConditionTypeCodeEnum implements ICodeEnum {
    /**
     * 常温
     */
    ORDINARY_TEMPERATURE("ORDINARY_TEMPERATURE", "常温"),
    /**
     * 冷藏
     */
    REFRIGERATE("REFRIGERATE", "冷藏"),
    /**
     * 阴凉
     */
    SHADE("SHADE", "阴凉");


    private String code;
    private String name;

    StorageConditionTypeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StorageConditionTypeCodeEnum fromCode(String code) {
        for (StorageConditionTypeCodeEnum goodsTypeCodeEnum : StorageConditionTypeCodeEnum.values()) {
            if (goodsTypeCodeEnum.code.equals(code)) {
                return goodsTypeCodeEnum;
            }
        }
        String error = code == null ? GlobalExt.ENUM_FROM_CODE_NULL :
                "".equals(code) ? GlobalExt.ENUM_FROM_CODE_BLANK : "code=" + code;
        throw new RuntimeException(StorageConditionTypeCodeEnum.class.getName() + ":" + GlobalExt.ENUM_FROM_CODE_ERROR + error);
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
